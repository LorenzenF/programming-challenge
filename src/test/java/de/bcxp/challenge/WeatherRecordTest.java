package de.bcxp.challenge;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WeatherRecordTest {

    @Test
    void testConstructorValid() {
        WeatherRecord weatherRecord = new WeatherRecord("1", 30, 15);

        assertEquals("1", weatherRecord.getDay());
        assertEquals(30, weatherRecord.getMaxTemperature());
        assertEquals(15, weatherRecord.getMinTemperature());
    }

    @Test
    void testConstructorShouldTrimDay() {
        WeatherRecord wr = new WeatherRecord(" Tag1 ", 30, 15);
        assertEquals("Tag1", wr.getDay());
    }

    @Test
    void testConstructorInvalidDay() {
        assertThrows(IllegalArgumentException.class, () -> {
            new WeatherRecord(null, 30, 15);
        });
    }

    @Test
    void testConstructorEmptyDay() {
        assertThrows(IllegalArgumentException.class, () -> {
            new WeatherRecord(" ", 30, 15);
        });
    }

    @Test
    void testConstructorNegativeValue() {
        WeatherRecord weatherRecord = new WeatherRecord("1", 10, -5);

        assertEquals("1", weatherRecord.getDay());
        assertEquals(10, weatherRecord.getMaxTemperature());
        assertEquals(-5, weatherRecord.getMinTemperature());
    }

    @Test
    void testConstructorMaxSameAsMin() {
        WeatherRecord weatherRecord = new WeatherRecord("1", 0, 0);

        assertEquals("1", weatherRecord.getDay());
        assertEquals(0, weatherRecord.getMaxTemperature());
        assertEquals(0, weatherRecord.getMinTemperature());
    }

    @Test
    void testConstructorMaxSmallerThanMin() {
        assertThrows(IllegalArgumentException.class, () -> {
            new WeatherRecord("1", -15, 15);
        });

    }
}
