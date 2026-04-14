package de.bcxp.challenge;

import java.util.List;

public class WeatherService {

    private final List<WeatherRecord> weatherRecordList;


    public WeatherService(List<WeatherRecord> weatherRecordList) {
        if (weatherRecordList == null || weatherRecordList.isEmpty()) {
            throw new IllegalArgumentException("weatherRecordList must not be null or empty");
        }
        this.weatherRecordList = List.copyOf(weatherRecordList);
    }

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

    private int getTemperatureRange(WeatherRecord weatherRecord) {
        return weatherRecord.getMaxTemperature() - weatherRecord.getMinTemperature();
    }
}
