package de.bcxp.challenge;

/**
 * Represents a single weather data record.
 *
 * <p>This class models a weather entry consisting of a day identifier,
 * a maximum temperature, and a minimum temperature. Basic validation is
 * performed to ensure a valid and consistent state.</p>
 */
public class WeatherRecord {
    private final String day;
    private final int maxTemperature;
    private final int minTemperature;

    /**
     * Creates a new {@code WeatherRecord}.
     *
     * @param day            identifier of the day (must not be null or empty)
     * @param maxTemperature maximum temperature of the day
     * @param minTemperature minimum temperature of the day
     * @throws IllegalArgumentException if {@code day} is null or empty
     *                                  or if {@code maxTemperature} is less than {@code minTemperature}
     */
    public WeatherRecord(String day, int maxTemperature, int minTemperature) {
        if (day == null || day.trim().isEmpty()) {
            throw new IllegalArgumentException("day must not be null or empty");
        }

        if (maxTemperature < minTemperature) {
            throw new IllegalArgumentException("maxTemperature must be >= minTemperature");
        }

        this.day = day.trim();;
        this.maxTemperature = maxTemperature;
        this.minTemperature = minTemperature;
    }

    public String getDay() {
        return day;
    }

    public int getMaxTemperature() {
        return maxTemperature;
    }

    public int getMinTemperature() {
        return minTemperature;
    }
}
