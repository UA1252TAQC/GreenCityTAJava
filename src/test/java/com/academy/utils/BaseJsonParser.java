package com.academy.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class BaseJsonParser {
    protected final ObjectMapper mapper;
    protected final JsonNode rootNode;

    protected BaseJsonParser(String source) {
        this.mapper = new ObjectMapper();
        try {
            this.rootNode = mapper.readTree(new File("src/test/resources/" + source));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
