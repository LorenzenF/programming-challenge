package de.bcxp.challenge;

import java.util.List;

/**
 * Service for analyzing weather data.
 *
 * <p>This service provides operations on a list of {@link WeatherRecord}
 * instances. It assumes that the provided records are valid and performs
 * calculations based on their values.</p>
 */
public class WeatherService {

    private final List<WeatherRecord> weatherRecordList;

    /**
     * Creates a new {@code WeatherService}.
     *
     * @param weatherRecordList list of weather records to be analyzed
     *                          (must not be {@code null} or empty)
     * @throws IllegalArgumentException if {@code weatherRecordList} is {@code null}
     *                                  or empty
     */
    public WeatherService(List<WeatherRecord> weatherRecordList) {
        if (weatherRecordList == null || weatherRecordList.isEmpty()) {
            throw new IllegalArgumentException("weatherRecordList must not be null or empty");
        }
        this.weatherRecordList = List.copyOf(weatherRecordList);
    }

    /**
     * Finds the day with the smallest temperature range.
     *
     * <p>The temperature range is defined as the difference between
     * maximum and minimum temperature.</p>
     *
     * <p>If multiple days have the same smallest temperature range,
     * the first occurrence is returned.</p>
     *
     * @return the identifier of the day with the smallest temperature range
     */
    public String findDayWithSmallestTemperatureRange() {
        WeatherRecord firstRecord = weatherRecordList.get(0);
        String dayWithSmallestTemperatureRange = firstRecord.getDay();
        int smallestTemperatureRange = getTemperatureRange(firstRecord);

        for (int i = 1; i < weatherRecordList.size(); i++) {
            WeatherRecord weatherRecord = weatherRecordList.get(i);
            int currentTemperatureRange = getTemperatureRange(weatherRecord);

            if (smallestTemperatureRange > currentTemperatureRange) {
                smallestTemperatureRange = currentTemperatureRange;
                dayWithSmallestTemperatureRange = weatherRecord.getDay();
            }
        }

        return dayWithSmallestTemperatureRange;
    }

    /**
     * Calculates the temperature range for a given weather record.
     *
     * @param weatherRecord the weather record
     * @return the difference between maximum and minimum temperature
     */
    private int getTemperatureRange(WeatherRecord weatherRecord) {
        return weatherRecord.getMaxTemperature() - weatherRecord.getMinTemperature();
    }
}
