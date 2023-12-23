package com.example.demo.service;

import com.example.demo.domain.FileManager;
import com.example.demo.domain.TopSecretCalculator;
import com.example.demo.model.Location;
import com.example.demo.model.Satellite;
import com.example.demo.model.TopSecretResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopSecretServiceImpl implements TopSecretService {

    private final FileManager fileManager;

    @Autowired
    public TopSecretServiceImpl(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    @Override
    public TopSecretResponse topsecret(List<Satellite> satellites) {
        try {
            float d1 = satellites.get(0).getDistance();
            float d2 = satellites.get(1).getDistance();
            float d3 = satellites.get(2).getDistance();

            Location location = TopSecretCalculator.getLocation(d1, d2, d3);
            String message = TopSecretCalculator.getMessage(satellites.stream()
                    .map(Satellite::getMessage)
                    .toArray(String[][]::new));

            if (location == null || message == null)
                throw new RuntimeException("Error al procesar datos");
            return new TopSecretResponse(location, message);
        } catch (Exception e) {
            throw new RuntimeException("Error al procesar datos", e);
        }
    }

    @Override
    public void saveSatelliteData(Satellite satellite) {
        try {
            fileManager.saveDataToFile(satellite);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar datos", e);
        }
    }

    @Override
    public TopSecretResponse getTopSecretData() {
        try {
            List<Satellite> satellites = fileManager.loadDataFromFile();
            return topsecret(satellites);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener datos", e);
        }
    }
}
