package com.example.demo.domain;

import com.example.demo.model.Satellite;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Component
public class FileManager {

    private static final String FILE_PATH = "satellite_data.json";
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final File storageFile = new File(FILE_PATH);


    public void saveDataToFile(Satellite satellite) throws IOException {
        try {
            objectMapper.writeValue(storageFile, satellite);
        } catch (IOException e) {
            throw new IOException("Error al grabar la informacion", e);
        }
    }

    public List<Satellite> loadDataFromFile() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Satellite> satellites;
        try {
            satellites = objectMapper.readValue(storageFile, new TypeReference<>() {});
        } catch (IOException e) {
            throw new IOException("Error al cargar la informacion", e);
        }
        return satellites;
    }

}
