package de.bcxp.challenge;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CountryServiceTest {
    @Test
    void testConstructorValid() {
        List<CountryRecord> countryRecordList = new ArrayList<>();
        countryRecordList.add(new CountryRecord("Germany", 83120520, 357386));

        CountryService countryService = new CountryService(countryRecordList);

        assertNotNull(countryService);
    }

    @Test
    void testConstructorNullList() {
        assertThrows(IllegalArgumentException.class, () -> new CountryService(null));
    }

    @Test
    void testConstructorEmptyList() {
        assertThrows(IllegalArgumentException.class, () -> new CountryService(List.of()));
    }

    @Test
    void testFindCountryWithHighestPopulationDensityValid() {
        List<CountryRecord> countryRecords = List.of(
                new CountryRecord("Austria", 8926000, 83855),
                new CountryRecord("Germany", 83120520, 357386)
        );

        CountryService countryService = new CountryService(countryRecords);

        assertEquals("Germany", countryService.findCountryWithHighestPopulationDensity());
    }

    @Test
    void testFindCountryWithHighestPopulationDensityValidSingleEntry() {
        List<CountryRecord> countryRecords = List.of(
                new CountryRecord("Germany", 83120520, 357386)
        );

        CountryService countryService = new CountryService(countryRecords);

        assertEquals("Germany", countryService.findCountryWithHighestPopulationDensity());
    }

    @Test
    void testFindCountryWithHighestPopulationDensityKeepsFirstOnTie() {
        List<CountryRecord> countryRecords = List.of(
                new CountryRecord("Germany", 83120520, 357386),
                new CountryRecord("CoincidentallyTheSameEntry", 83120520, 357386)
        );

        CountryService countryService = new CountryService(countryRecords);

        assertEquals("Germany", countryService.findCountryWithHighestPopulationDensity());
    }
}
