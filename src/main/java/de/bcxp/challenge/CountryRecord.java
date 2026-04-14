package de.bcxp.challenge;

/**
 * Represents a single country data record.
 *
 * <p>This class models a country entry consisting of a name,
 * its population, and its area in square kilometers. Basic validation
 * is performed to ensure a valid and consistent state.</p>
 */
public class CountryRecord {
    private final String name;
    private final int population;
    private final int area;

    /**
     * Creates a new {@code CountryRecord}.
     *
     * @param name       name of the country (must not be null or empty)
     * @param population population of the country (must not be negative)
     * @param area       area of the country in square kilometers (must be greater than 0)
     * @throws IllegalArgumentException if {@code name} is null or empty,
     *                                  {@code population} is negative,
     *                                  or {@code area} is less than or equal to 0
     */
    public CountryRecord(String name, int population, int area) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("name must not be null or empty");
        }

        if (area <= 0) {
            throw new IllegalArgumentException("area must be greater than 0");
        }

        if (population < 0) {
            throw new IllegalArgumentException("population must not be negative");
        }

        this.name = name.trim();
        this.population = population;
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public int getPopulation() {
        return population;
    }

    public int getArea() {
        return area;
    }

}

