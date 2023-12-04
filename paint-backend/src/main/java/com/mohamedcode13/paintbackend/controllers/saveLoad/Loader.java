package com.mohamedcode13.paintbackend.controllers.saveLoad;

import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.xml.crypto.Data;

public class Loader {

    static public DataStored loadJson(String json) {




        DataStored dataStored = new DataStored();
        ObjectMapper mapper = new ObjectMapper();

        try {
            dataStored = mapper.readValue(json, new TypeReference<DataStored>(){});
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dataStored;


    }

    public static DataStored loadXml(String xml) {

        return null;
    }


}



