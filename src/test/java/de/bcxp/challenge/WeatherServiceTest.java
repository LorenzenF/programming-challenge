package de.bcxp.challenge;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class WeatherServiceTest {

    @Test
    void testConstructorValid() {
        List<WeatherRecord> weatherRecordList = List.of(
                (new WeatherRecord("1", 30, 15))
        );

        WeatherService weatherService = new WeatherService(weatherRecordList);

        assertNotNull(weatherService);
    }

    @Test
    void testConstructorNullList() {
        assertThrows(IllegalArgumentException.class, () -> {
            new WeatherService(null);
        });
    }

    @Test
    void testConstructorEmptyList() {
        assertThrows(IllegalArgumentException.class, () -> {
            new WeatherService(List.of());
        });
    }

    @Test
    void testFindDayWithSmallestTemperatureRangeValid() {
        List<WeatherRecord> weatherRecordList = List.of(
                new WeatherRecord("1", 30, 15),
                new WeatherRecord("2", 25, 20)
        );

        WeatherService weatherService = new WeatherService(weatherRecordList);

        assertEquals("2", weatherService.findDayWithSmallestTemperatureRange());
    }

    @Test
    void testFindDayWithSmallestTemperatureRangeWithSingleEntry() {
        List<WeatherRecord> list = List.of(new WeatherRecord("1", 30, 15));

        WeatherService service = new WeatherService(list);

        assertEquals("1", service.findDayWithSmallestTemperatureRange());
    }

    @Test
    void testFindDayWithSmallestTemperatureRangeKeepsFirstOnTie() {
        List<WeatherRecord> weatherRecordList = List.of(
                new WeatherRecord("1", 30, 15),
                new WeatherRecord("2", 25, 10)
        );

        WeatherService weatherService = new WeatherService(weatherRecordList);

        assertEquals("1", weatherService.findDayWithSmallestTemperatureRange());
    }

}
