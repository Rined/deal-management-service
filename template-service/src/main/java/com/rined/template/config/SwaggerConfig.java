package com.rined.template.config;

import com.rined.template.controllers.TemplateController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .globalOperationParameters(userHeaderParameter())
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage(TemplateController.class.getPackage().getName()))
                .build();
    }

    private List<Parameter> userHeaderParameter() {
        return Arrays.asList(
                new ParameterBuilder()
                        .name("userId")
                        .description("Id of user")
                        .modelRef(new ModelRef("string"))
                        .parameterType("header")
                        .required(true)
                        .defaultValue("userId")
                        .build(),
                new ParameterBuilder()
                        .name("username")
                        .description("Name of user")
                        .modelRef(new ModelRef("string"))
                        .parameterType("header")
                        .required(true)
                        .defaultValue("username")
                        .build(),
                new ParameterBuilder()
                        .name("userEmail")
                        .description("Email of user")
                        .modelRef(new ModelRef("string"))
                        .parameterType("header")
                        .required(true)
                        .defaultValue("userEmail")
                        .build()
        );
    }
}
