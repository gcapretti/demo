package com.example.demo.service;


import com.example.demo.model.Satellite;
import com.example.demo.model.TopSecretResponse;

import java.util.List;

public interface TopSecretService {
    TopSecretResponse topsecret(List<Satellite> satellites);

    void saveSatelliteData(Satellite satellite);

    TopSecretResponse getTopSecretData();

}
