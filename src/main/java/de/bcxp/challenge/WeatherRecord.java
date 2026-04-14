package de.bcxp.challenge;

public class WeatherRecord {
    private final String day;
    private final int maxTemperature;
    private final int minTemperature;


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
