package com.rined.deal.batch;

import com.rined.deal.model.Deal;
import com.rined.deal.properties.BatchProperties;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.SimpleStepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.builder.MongoItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.data.mongodb.core.MongoOperations;

import java.io.IOException;
import java.util.List;

@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class BatchConfig {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final MongoOperations template;
    private final BatchProperties properties;

    @Bean
    public ItemReader<Deal> reader() throws IOException {
        ResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = patternResolver.getResources(properties.getDirectoryPath());

        DealItemReader mapper = new DealItemReader(new DealJsonObjectReader());
        mapper.setName("dealLineMapper");

        MultiResourceItemReader<Deal> reader = new MultiResourceItemReader<>();
        reader.setResources(resources);
        reader.setStrict(true);
        reader.setDelegate(mapper);

        return reader;
    }

    @Bean
    public ItemWriter<Deal> writer() {
        return new MongoItemWriterBuilder<Deal>()
                .collection("deal")
                .template(template)
                .build();
    }

    @Bean
    public Job uploadDealJob(Step uploadDealStep) {
        return jobBuilderFactory.get("uploadDealJob")
                .flow(uploadDealStep)
                .end()
                .build();
    }

    @Bean
    public Step uploadDealStep(ItemWriter<Deal> writer, ItemReader<Deal> reader) {
        return stepBuilderFactory
                .get("uploadStep")
                .<Deal, Deal>chunk(properties.getChunk())
                .reader(reader)
                .writer(writer)
                .build();
    }
}
