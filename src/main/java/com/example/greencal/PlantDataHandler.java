package com.example.greencal;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class PlantDataHandler {
    public static void savePlantToFile(Plant plant, String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule()); // Ajoutez cette ligne
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS); // Ajoutez cette ligne pour conserver le format de date
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        File file = new File(filePath);
        try {
            objectMapper.writeValue(file, plant);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
