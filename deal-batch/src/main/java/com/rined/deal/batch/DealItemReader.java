package com.rined.deal.batch;

import com.rined.deal.model.Deal;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.file.ResourceAwareItemReaderItemStream;
import org.springframework.batch.item.json.JsonObjectReader;
import org.springframework.batch.item.support.AbstractItemCountingItemStreamItemReader;
import org.springframework.core.io.Resource;

@RequiredArgsConstructor
public class DealItemReader extends AbstractItemCountingItemStreamItemReader<Deal> implements ResourceAwareItemReaderItemStream<Deal> {
    private Resource resource;
    private JsonObjectReader<Deal> jsonObjectReader;

    DealItemReader(JsonObjectReader<Deal> jsonObjectReader) {
        this.jsonObjectReader = jsonObjectReader;
    }

    @Override
    public void setResource(Resource resource) {
        this.resource = resource;
    }

    @Override
    protected Deal doRead() throws Exception {
        return jsonObjectReader.read();
    }

    @Override
    protected void doOpen() throws Exception {
        if (resource.exists()) {
            jsonObjectReader.open(resource);
        }
    }

    @Override
    protected void doClose() throws Exception {
        jsonObjectReader.close();
    }
}
