package com.rined.deal.batch;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rined.deal.model.Deal;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.json.JsonObjectReader;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;

public class DealJsonObjectReader implements JsonObjectReader<Deal> {
    private final ObjectMapper mapper = new ObjectMapper();
    private JsonParser jsonParser;
    private InputStream inputStream;

    @Override
    public void open(Resource resource) throws Exception {
        this.inputStream = resource.getInputStream();
        this.jsonParser = this.mapper.getFactory().createParser(this.inputStream);
    }

    @Override
    public Deal read() {
        try {
            return jsonParser.nextToken() == JsonToken.START_OBJECT
                    ? mapper.readValue(jsonParser, Deal.class) : null;
        } catch (IOException e) {
            throw new ParseException("Unable to read next JSON object", e);
        }
    }

    @Override
    public void close() throws Exception {
        this.inputStream.close();
        this.jsonParser.close();
    }
}
