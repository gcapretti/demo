package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class Satellite {

    private long id;
    private String name;
    private Location location;
    private float distance;
    private String[] message;

    public Satellite() {}

    public Satellite(
            String name,
            float distance,
            String[] message) {
        this.name = name;
        this.distance = distance;
        this.message = message;
    }

    @JsonCreator
    public Satellite(
            @JsonProperty("name") String name,
            @JsonProperty("location") Location location,
            @JsonProperty("distance") float distance,
            @JsonProperty("message") String[] message) {
        this.name = name;
        this.location = location;
        this.distance = distance;
        this.message = message;
    }

    public static Satellite fromSatelliteRequest(String name, SatelliteRequest satelliteRequest) {
        Satellite satellite = new Satellite(
                name,
                satelliteRequest.getDistance(),
                satelliteRequest.getMessage()
        );
        return satellite;
    }

    public static List<Satellite> fromSatellitesRequest(List<SatelliteRequest> requests) {
        return requests.stream()
                .map(request -> Satellite.fromSatelliteRequest(request.getName(), request))
                .collect(Collectors.toList());
    }
}