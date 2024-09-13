package com.academy.utils;

import com.fasterxml.jackson.databind.JsonNode;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TestUtils extends BaseJsonUtils {
    public TestUtils() {
        super("testdata.json");
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
}
