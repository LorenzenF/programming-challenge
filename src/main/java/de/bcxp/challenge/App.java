package de.bcxp.challenge;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

/**
 * The entry class for your solution. This class is only aimed as starting point and not intended as baseline for your software
 * design. Read: create your own classes and packages as appropriate.
 */
public final class App {

    private static final NumberFormat nf = NumberFormat.getInstance(Locale.GERMANY);

    /**
     * This is the main entry method of your program.
     *
     * @param args The CLI arguments passed
     */
    public static void main(String... args) {
        CsvDataReader reader = new CsvDataReader();

        solveWeatherChallenge(reader);
        solveCountryChallenge(reader);
    }

    /**
     * Solves the weather challenge by reading the weather data,
     * delegating the calculation to {@link WeatherService},
     * and printing the result.
     *
     * @param reader the CSV data reader used to load the data
     */
    private static void solveWeatherChallenge(CsvDataReader reader) {
        WeatherService weatherService = new WeatherService(
                reader.read("de/bcxp/challenge/weather.csv", ",", 3, App::toWeatherRecord)
        );

        String dayWithSmallestTempSpread = weatherService.findDayWithSmallestTemperatureRange();
        System.out.printf("Day with smallest temperature spread: %s%n", dayWithSmallestTempSpread);
    }

    /**
     * Solves the country challenge by reading the country data,
     * delegating the calculation to {@link CountryService},
     * and printing the result.
     *
     * @param reader the CSV data reader used to load the data
     */
    private static void solveCountryChallenge(CsvDataReader reader) {
        CountryService countryService = new CountryService(
                reader.read("de/bcxp/challenge/countries.csv", ";", 5, App::toCountryRecord)
        );

        String countryWithHighestPopulationDensity = countryService.findCountryWithHighestPopulationDensity();
        System.out.printf("Country with highest population density: %s%n", countryWithHighestPopulationDensity);
    }

    /**
     * Maps a CSV row to a {@link CountryRecord}.
     * <p>
     * Parses numeric values using the German locale to correctly handle
     * formatting (e.g. thousands separators).
     *
     * @param values the CSV row values
     * @return the mapped {@link CountryRecord}
     * @throws IllegalArgumentException if the numeric values cannot be parsed
     */
    private static CountryRecord toCountryRecord(String[] values) {
        try {
            return new CountryRecord(
                    values[0].trim(),
                    nf.parse(values[3].trim()).intValue(),
                    nf.parse(values[4].trim()).intValue()
            );
        } catch (ParseException e) {
            throw new IllegalArgumentException("Failed to parse country data", e);
        }
    }

    /**
     * Maps a CSV row to a {@link WeatherRecord}.
     *
     * @param values the CSV row values
     * @return the mapped {@link WeatherRecord}
     * @throws NumberFormatException if numeric values cannot be parsed
     */
    private static WeatherRecord toWeatherRecord(String[] values) {
        return new WeatherRecord(
                values[0].trim(),
                Integer.parseInt(values[1].trim()),
                Integer.parseInt(values[2].trim())
        );
    }
}
