package com.yatin.utils;

import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

public class RestUtils {

    private final static ObjectMapper mapper = new ObjectMapper();

    public static Map<String, Object> readAsMap(String value) {
        return Exceptions.uncheck(
                () -> mapper.readValue(
                        value,
                        new TypeReference<Map<String, Object>>() {
                        }
                ));
    }

}
