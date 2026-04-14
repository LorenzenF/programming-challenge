package de.bcxp.challenge;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WeatherDataReader {

    public List<WeatherRecord> read(){
        String path = "de/bcxp/challenge/weather.csv";
        InputStream is = getClass().getClassLoader().getResourceAsStream(path);

        if (is == null) {
            throw new IllegalArgumentException(path + " not found");
        }

        List<WeatherRecord> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
            String line;

            // skip blank lines and find first non-empty line (header)
            String header;
            do {
                header = br.readLine();

                // null = end of file reached without finding a valid header
                if (header == null) {
                    throw new IllegalArgumentException("CSV file has no header or is empty");
                }
            } while (header.trim().isEmpty());

            String[] headerValues = header.split(",");
            if(headerValues.length < 3)
                throw new IllegalArgumentException("Invalid CSV header (expected at least " + 3 + " columns): " + Arrays.toString(headerValues));

            while ((line = br.readLine()) != null) {
                // skip empty lines
                if (line.trim().isEmpty())
                    continue;

                String[] values = line.split(",");

                // check if too few columns
                if (values.length < 3)
                    continue;

                WeatherRecord weatherRecord = new WeatherRecord(
                        values[0].trim(), Integer.parseInt(values[1].trim()), Integer.parseInt(values[2].trim())
                );
                records.add(weatherRecord);

            }

        } catch (IOException e) {
            throw new RuntimeException("Error reading csv data", e);
        }
        return records;
    }

}
