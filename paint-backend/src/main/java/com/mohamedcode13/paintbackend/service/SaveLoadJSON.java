package com.mohamedcode13.paintbackend.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mohamedcode13.paintbackend.models.AbstractShape;
import org.springframework.stereotype.Service;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

@Service
public class SaveLoadJSON {
    public String saveJson(List<AbstractShape> shapes) {
        ObjectMapper objectMapper = new ObjectMapper();
        StringWriter writer = new StringWriter();

        try {
            objectMapper.writeValue(writer, shapes);
            return writer.toString();
        } catch (Exception e) {
            return null;
        }
    }

    public List<AbstractShape> loadJson(String jsonData) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.readValue(jsonData, objectMapper.getTypeFactory().constructCollectionType(List.class, AbstractShape.class));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
