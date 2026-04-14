package de.bcxp.challenge;

import java.util.List;

/**
 * Service for analyzing country data.
 *
 * <p>This service provides operations on a list of {@link CountryRecord}
 * instances. It assumes that the provided records are valid and performs
 * calculations based on their values.</p>
 */
public class CountryService {

    private final List<CountryRecord> countryRecords;

    /**
     * Creates a new {@code CountryService}.
     *
     * @param countryRecords list of country records to be analyzed
     *                       (must not be {@code null} or empty)
     * @throws IllegalArgumentException if {@code countryRecords} is {@code null}
     *                                  or empty
     */
    public CountryService(List<CountryRecord> countryRecords) {
        if (countryRecords == null || countryRecords.isEmpty()) {
            throw new IllegalArgumentException("countryRecords must not be null or empty");
        }

        this.countryRecords = List.copyOf(countryRecords);
    }

    /**
     * Finds the country with the highest population density.
     *
     * <p>Population density is calculated as population divided by area.</p>
     *
     * <p>If multiple countries have the same highest population density,
     * the first occurrence is returned.</p>
     *
     * @return the name of the country with the highest population density
     */
    public String findCountryWithHighestPopulationDensity() {
        CountryRecord firstRecord = countryRecords.get(0);
        String countryNameWithHighestDensity = firstRecord.getName();
        double highestPopulationDensity  = getPopulationDensity(firstRecord);

        for (int i = 1; i < countryRecords.size(); i++) {
            CountryRecord currentRecord = countryRecords.get(i);
            double currentPopulationDensity = getPopulationDensity(currentRecord);

            if (highestPopulationDensity  < currentPopulationDensity) {
                highestPopulationDensity  = currentPopulationDensity;
                countryNameWithHighestDensity = currentRecord.getName();
            }
        }

        return countryNameWithHighestDensity;
    }

    /**
     * Calculates the population density for a given country.
     *
     * <p>Population density is calculated as population divided by area.</p>
     *
     * @param country the country record
     * @return the population density
     */
    private double getPopulationDensity(CountryRecord country) {
        return (double) country.getPopulation() / country.getArea();
    }
}
