package com.atcsimulator.app.core.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;

public class JsonUtils {

    public static <T> T mapFromJson(String path, TypeReference<T> ref) throws IOException {
        ClassPathResource resource = new ClassPathResource(path);
        InputStream stream = resource.getInputStream();

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(stream, ref);
    }
}
