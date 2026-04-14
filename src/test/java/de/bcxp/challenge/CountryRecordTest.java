package de.bcxp.challenge;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CountryRecordTest {
    @Test
    void testConstructorValid() {
        CountryRecord countryRecord = new CountryRecord("Deutschland", 83120520, 357386);

        assertEquals("Deutschland", countryRecord.getName());
        assertEquals(83120520, countryRecord.getPopulation());
        assertEquals(357386, countryRecord.getArea());
    }

    @Test
    void testConstructorShouldTrimName() {
        CountryRecord cr = new CountryRecord(" Deutschland ", 1000, 100);
        assertEquals("Deutschland", cr.getName());
    }

    @Test
    void testConstructorNullName() {
        assertThrows(IllegalArgumentException.class, () -> {
            new CountryRecord(null, 1000, 100);
        });
    }

    @Test
    void testConstructorEmptyName() {
        assertThrows(IllegalArgumentException.class, () -> {
            new CountryRecord(" ", 1000, 100);
        });
    }

    @Test
    void testConstructorInvalidPopulation() {
        assertThrows(IllegalArgumentException.class, () -> {
            new CountryRecord("Deutschland", -1000, 100);
        });
    }

    @Test
    void testConstructorInvalidArea() {
        assertThrows(IllegalArgumentException.class, () -> {
            new CountryRecord("Deutschland", 1000, -100);
        });
    }

    @Test
    void testConstructorAreaZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            new CountryRecord("Deutschland", 1000, 0);
        });
    }


}
