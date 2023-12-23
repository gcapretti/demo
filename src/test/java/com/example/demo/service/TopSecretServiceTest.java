package com.example.demo.service;

import com.example.demo.domain.FileManager;
import com.example.demo.model.Satellite;
import com.example.demo.model.TopSecretResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class TopSecretServiceTest {

    @Autowired
    TopSecretService topSecretService;
    @MockBean
    private FileManager fileManager;

    @Test
    public void testTopsecret() {
        List<Satellite> satellites = Arrays.asList(
                new Satellite("kenobi", 100.0f, new String[]{"este", "", "", "mensaje", ""}),
                new Satellite("skywalker", 115.5f, new String[]{"", "es", "", "", "secreto"}),
                new Satellite("sato", 142.7f, new String[]{"", "", "un", "", ""})
        );
        TopSecretResponse topSecretResponse = topSecretService.topsecret(satellites);
        assertNotNull(topSecretResponse);
        assertEquals("este es un mensaje secreto", topSecretResponse.getMessage());
    }

    @Test
    public void testTopsecretError() {
        List<Satellite> satellites = Arrays.asList(
                new Satellite("kenobi", 0.0f, new String[]{"este", "", "", "mensaje", ""}),
                new Satellite("skywalker", 0.0f, new String[]{"", "es", "", "", "secreto"}),
                new Satellite("sato", 0.0f, new String[]{"", "", "un", "", ""})
        );
        assertThrows(RuntimeException.class, () -> topSecretService.topsecret(satellites));
    }

    @Test
    void testInvalidLocation() {
        List<Satellite> satellites = Arrays.asList(
                new Satellite("kenobi", 10.0f, new String[]{"este", "", "", "mensaje", ""}),
                new Satellite("skywalker", 20.0f, new String[]{"", "es", "", "", "secreto"}),
                new Satellite("sato", 50.0f, new String[]{"", "", "un", "", ""})
        );
        assertThrows(RuntimeException.class, () -> topSecretService.topsecret(satellites));
    }

    @Test
    void testSaveSatelliteData() throws IOException {
        Satellite satellite = new Satellite("kenobi", 100.0f, new String[]{"este", "", "", "mensaje", ""});
        topSecretService.saveSatelliteData(satellite);
        // Verifica que el método saveDataToFile se haya llamado con el satélite correcto
        verify(fileManager, times(1)).saveDataToFile(satellite);
    }

    @Test
    void testGetTopSecretData() throws IOException {
        List<Satellite> mockSatellites = Arrays.asList(
                new Satellite("kenobi", 100.0f, new String[]{"este", "", "", "mensaje", ""}),
                new Satellite("skywalker", 115.5f, new String[]{"", "es", "", "", "secreto"}),
                new Satellite("sato", 142.7f, new String[]{"", "", "un", "", ""})
        );
        when(fileManager.loadDataFromFile()).thenReturn(mockSatellites);
        TopSecretResponse result = topSecretService.getTopSecretData();
        assertEquals("este es un mensaje secreto", result.getMessage());
    }

}
