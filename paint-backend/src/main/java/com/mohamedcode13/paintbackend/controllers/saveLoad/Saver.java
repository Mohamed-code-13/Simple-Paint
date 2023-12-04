package com.mohamedcode13.paintbackend.controllers.saveLoad;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Saver {


    static public String saveJson(DataStored dataStored) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(dataStored);

        return json;
    }

    static public String saveXml(DataStored dataStored) throws JsonProcessingException {

        return null;
    }
}
