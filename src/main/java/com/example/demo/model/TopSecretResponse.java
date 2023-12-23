package com.example.demo.model;

import lombok.Data;

@Data
public class TopSecretResponse {
    private Location position;
    private String message;

    public TopSecretResponse(Location position, String message) {
        this.position = position;
        this.message = message;
    }
}
