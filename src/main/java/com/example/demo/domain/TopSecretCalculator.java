package com.example.demo.domain;

import com.example.demo.model.Location;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TopSecretCalculator {

    private static final float MARGIN_OF_INFINITY = 1e8f;

    private static final float MARGIN_OF_ZERO = 1e-6f;
    private static final float[] KENOBI = {-500, -200};
    private static final float[] SKYWALKER = {100, -100};
    private static final float[] SATO = {500, 100};

    public static Location getLocation(float distanceKenobi, float distanceSkywalker, float distanceSato) {
        if(!areDistancesValid(distanceKenobi, distanceSkywalker, distanceSato)) {
            return null;
        }
        float x = calcularX(KENOBI[0], KENOBI[1], SKYWALKER[0], SKYWALKER[1], SATO[0], SATO[1], distanceKenobi, distanceSkywalker, distanceSato);
        float y = calcularY(KENOBI[0], KENOBI[1], SKYWALKER[0], SKYWALKER[1], distanceKenobi, distanceSkywalker, x);
        return new Location((float) (Math.round(x * 10.0) / 10.0), (float) (Math.round(y * 10.0) / 10.0));
    }

    private static float calcularX(float x1, float y1, float x2, float y2, float x3, float y3, float r1, float r2, float r3) {
        float c1 = (float) (Math.pow(r1, 2) - Math.pow(r2, 2) + Math.pow(x2, 2) - Math.pow(x1, 2) + Math.pow(y2, 2) - Math.pow(y1, 2));
        float c2 = (float) (Math.pow(r1, 2) - Math.pow(r3, 2) + Math.pow(x3, 2) - Math.pow(x1, 2) + Math.pow(y3, 2) - Math.pow(y1, 2));
        float d = (2 * (x2 - x1) * (2 * (y3 - y1))) - (2 * (x3 - x1) * (2 * (y2 - y1)));
        float n = c1 * (2 * (y3 - y1)) - (c2 * (2 * (y2 - y1)));
        if (!isValidCalculation(n) || !isValidCalculation(d) || isZero(d)) {
            throw new RuntimeException("Error al procesar datos");
        }
        return n / d;
    }

    private static float calcularY(float x1, float y1, float x2, float y2, float r1, float r2, float x) {
        float c1 = (float) (Math.pow(r1, 2) - Math.pow(r2, 2) + Math.pow(x2, 2) - Math.pow(x1, 2) + Math.pow(y2, 2) - Math.pow(y1, 2));
        return (c1 - (2 * (x2 - x1)) * x ) / (2 * (y2 - y1));
    }

    public static String getMessage(String[]... messages) {
        // Cree una lista para almacenar las palabras.
        List<String> words = new ArrayList<>();
        boolean hasNonEmpty = false;
        for (int i = 0; i < messages[0].length; i++) {
            for(int j = 0; j < messages.length; j++) {
                if (!messages[j][i].equals("")) {
                    words.add(messages[j][i]);
                    hasNonEmpty = true;
                    break;
                }
            }
            if (!hasNonEmpty) {
                return null;
            }
        }
        // Combine las palabras en una cadena con espacios.
        return String.join(" ", words).trim();
    }

    private static boolean areDistancesValid(float distanceKenobi, float distanceSkywalker, float distanceSato) {
        // Verifica que sean positivas y que almenos una no sea cero para evitar divisiones por cero.
        return  distanceKenobi >= 0 && distanceSkywalker >= 0 && distanceSato >= 0
                && (distanceKenobi != 0 || distanceSkywalker != 0 || distanceSato != 0);
    }

    private static boolean isValidCalculation(float value) {
        return !Float.isNaN(value) && !Float.isInfinite(value)
                && Math.abs(value) < MARGIN_OF_INFINITY;
    }

    private static boolean isZero(float value) {
        return Math.abs(value) < MARGIN_OF_ZERO;
    }

}
