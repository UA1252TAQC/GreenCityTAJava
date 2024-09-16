package com.academy.utils;

import com.academy.utils.props.ConfigProperties;
import com.fasterxml.jackson.databind.JsonNode;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TestUtils extends BaseJsonUtils {
	private final MailUtils mailUtils;
	private final ConfigProperties configProperties;

	public TestUtils() {
		super("testdata.json");
		this.mailUtils = new MailUtils();
		this.configProperties = new ConfigProperties();
	}

	public Iterator<Object[]> getTestCases(Method method) {
		List<Object[]> data = new ArrayList<>();
		String dataType = method.getName().replace("dp", "");

		JsonNode dataNodes = rootNode.get(dataType);
		if (dataNodes != null) {
			for (JsonNode node : dataNodes) {
				Object[] row = parseRow(node, method);
				data.add(row);
			}
		}
		return data.iterator();
	}

	private Object[] parseRow(JsonNode node, Method method) {
		Class<?>[] parameterTypes = method.getParameterTypes();
		Object[] row = new Object[parameterTypes.length];
		for (int i = 0; i < parameterTypes.length; i++) {
			JsonNode cell = node.get(i);
			row[i] = convertJsonToType(cell, parameterTypes[i]);
		}
		return row;
	}

	private Object convertJsonToType(JsonNode cell, Class<?> type) {
		if (isShouldInjectData(cell,"GENERATE_TEMPORARY_EMAIL")) return mailUtils.createNewMailCredentials();
		if (isShouldInjectData(cell,"EXTRACT_GOOGLE_EMAIL")) return configProperties.getGoogleEmail();
		if (isShouldInjectData(cell,"EXTRACT_GOOGLE_PASSWORD")) return configProperties.getGooglePassword();
		if (isShouldInjectData(cell,"EXTRACT_GOOGLE_NAME")) return configProperties.getGoogleName();

		if (type == String.class) {
			return cell.asText();
		} else if (type == boolean.class || type == Boolean.class) {
			return cell.asBoolean();
		} else if (type == int.class || type == Integer.class) {
			return cell.asInt();
		} else if (type == double.class || type == Double.class) {
			return cell.asDouble();
		} else if (type == long.class || type == Long.class) {
			return cell.asLong();
		} else {
			return cell.toString();
		}
	}

	private boolean isShouldInjectData(JsonNode cell, String param) {
		if (cell.asText().equals(param)) {
			try {
				return true;
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		return false;
	}
}
