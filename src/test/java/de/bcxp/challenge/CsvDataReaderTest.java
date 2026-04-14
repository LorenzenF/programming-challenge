package de.bcxp.challenge;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CsvDataReaderTest {

    private final CsvDataReader reader = new CsvDataReader();

    @Test
    void testReadValidWeatherCsv() {
        List<WeatherRecord> records = reader.read(
                "de/bcxp/challenge/valid-weather-test.csv",
                ",",
                3,
                this::parseWeatherRecord
        );

        assertEquals(2, records.size());
        assertEquals("1", records.get(0).getDay());
        assertEquals(30, records.get(0).getMaxTemperature());
        assertEquals(15, records.get(0).getMinTemperature());
    }

    @Test
    void testReadFileNotFound() {
        assertThrows(IllegalArgumentException.class, () -> reader.read(
                "de/bcxp/challenge/does-not-exist.csv",
                ",",
                3,
                this::parseWeatherRecord
        ));
    }

    @Test
    void testReadEmptyCsv() {
        assertThrows(IllegalArgumentException.class, () -> reader.read(
                "de/bcxp/challenge/empty-test.csv",
                ",",
                3,
                this::parseWeatherRecord
        ));
    }

    @Test
    void testReadInvalidColumnCount() {
        assertThrows(IllegalArgumentException.class, () -> reader.read(
                "de/bcxp/challenge/invalid-columns-test.csv",
                ",",
                3,
                this::parseWeatherRecord
        ));
    }

    @Test
    void testReadInvalidNumberFormat() {
        assertThrows(IllegalArgumentException.class, () -> reader.read(
                "de/bcxp/challenge/invalid-number-test.csv",
                ",",
                3,
                this::parseWeatherRecord
        ));
    }

    @Test
    void testReadWeatherCsvWithBlankLines() {
        List<WeatherRecord> records = reader.read(
                "de/bcxp/challenge/blank-lines-test.csv",
                ",",
                3,
                this::parseWeatherRecord
        );

        assertEquals(2, records.size());

        assertEquals("1", records.get(0).getDay());
        assertEquals("2", records.get(1).getDay());
    }

    private WeatherRecord parseWeatherRecord(String[] values) {
        return new WeatherRecord(
                values[0].trim(),
                Integer.parseInt(values[1].trim()),
                Integer.parseInt(values[2].trim())
        );
    }


}