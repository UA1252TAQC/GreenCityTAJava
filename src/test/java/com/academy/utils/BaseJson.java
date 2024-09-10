package com.academy.utils;

import java.io.File;
import java.io.IOException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BaseJson {
	protected final ObjectMapper mapper;
	protected final JsonNode rootNode;

	protected BaseJson(String source) {
		this.mapper = new ObjectMapper();
		try {
			this.rootNode = mapper.readTree(new File("src/test/resources/" + source));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
