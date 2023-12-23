package com.example.demo.domain;

import com.example.demo.model.Location;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class TopSecretCalculatorTest {

    @Test
    void testValidDistances1() {
        // Distancias de ejemplo al emisor
        float distanceKenobi = 707.1f;
        float distanceSkywalker = 412.3f;
        float distanceSato = 538.5f;

        Location emitterLocation = TopSecretCalculator.getLocation(distanceKenobi, distanceSkywalker, distanceSato);
        System.out.println("El emisor está en las coordenadas: x=" + emitterLocation.getX() + ", y=" + emitterLocation.getY());
    }

    @Test
    void testValidDistances2() {
        // Distancias de ejemplo al emisor
        float distanceKenobi = 640.3f;
        float distanceSkywalker = 447.2f;
        float distanceSato = 632.5f;

        Location emitterLocation = TopSecretCalculator.getLocation(distanceKenobi, distanceSkywalker, distanceSato);
        System.out.println("El emisor está en las coordenadas: x=" + emitterLocation.getX() + ", y=" + emitterLocation.getY());
    }

    @Test
    public void testDecodeMessage() {
        // Mensajes recibidos en cada satélite
        String[] messages1 = {"este", "es", "un", "mensaje"};
        String[] messages2 = {"este", "", "un", "mensaje"};
        String[] messages3 = {"", "es", "", "mensaje"};
        String expected = "este es un mensaje";
        String actual = TopSecretCalculator.getMessage(messages1, messages2, messages3);
        Assertions.assertEquals(expected, actual);
    }

}
