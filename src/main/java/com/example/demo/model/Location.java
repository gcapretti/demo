package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class Location {
    @JsonFormat(shape = JsonFormat.Shape.NUMBER_FLOAT, pattern = "0.0")
    private float x;

    @JsonFormat(shape = JsonFormat.Shape.NUMBER_FLOAT, pattern = "0.0")
    private float y;

    public Location(float x, float y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Location{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
