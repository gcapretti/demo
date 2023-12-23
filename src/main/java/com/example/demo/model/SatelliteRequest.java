package com.example.demo.model;

import lombok.Data;

@Data
public class SatelliteRequest {
    private String name;
    private float distance;
    private String[] message;

    public SatelliteRequest() {}

    public SatelliteRequest(String name, float distance, String[] message) {
        this.name = name;
        this.distance = distance;
        this.message = message;
    }
}
