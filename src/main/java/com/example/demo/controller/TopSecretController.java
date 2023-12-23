package com.example.demo.controller;

import com.example.demo.model.Satellite;
import com.example.demo.model.SatelliteRequest;
import com.example.demo.model.TopSecretResponse;
import com.example.demo.service.TopSecretService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class TopSecretController {

    private final TopSecretService topSecretService;

    @Autowired
    public TopSecretController(TopSecretService topSecretService) {
        this.topSecretService = topSecretService;
    }

    @PostMapping("/topsecret")
    public ResponseEntity<TopSecretResponse> topsecret(@RequestBody List<SatelliteRequest> satellites) {
        try {
            return ResponseEntity.ok(topSecretService.topsecret(Satellite.fromSatellitesRequest(satellites)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/topsecret_split/{satellite_name}")
    public ResponseEntity saveSatelliteData(@PathVariable String satelliteName, @RequestBody SatelliteRequest satellite) {
        try {
            topSecretService.saveSatelliteData(Satellite.fromSatelliteRequest(satelliteName, satellite));
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/topsecret_split/{satellite_name}")
    public ResponseEntity<TopSecretResponse> getTopSecretData() {
        System.out.println("hola");
        try {
            TopSecretResponse response = topSecretService.getTopSecretData();
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}