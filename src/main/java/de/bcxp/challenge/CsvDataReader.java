package de.bcxp.challenge;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Pattern;

/**
 * Generic CSV reader that reads CSV files from the classpath and maps each data row
 * to a target type using a provided mapping function.
 *
 * <p>The reader validates the basic CSV structure (header and minimum column count)
 * but does not interpret domain-specific semantics. Parsing of individual fields is
 * delegated to the provided parser function.</p>
 */
public class CsvDataReader {

    /**
     * Reads a CSV file from the classpath and maps each data row to an object of type {@code T}.
     *
     * <p>The method performs the following steps:</p>
     * <ul>
     *   <li>Loads the CSV file from the classpath</li>
     *   <li>Skips leading empty lines</li>
     *   <li>Validates the presence and structure of the header</li>
     *   <li>Validates that each data row contains at least the expected number of columns</li>
     *   <li>Maps each valid row using the provided parser function</li>
     * </ul>
     *
     * @param path            the classpath location of the CSV file
     * @param delimiter       the column delimiter used in the CSV file
     * @param expectedColumns the minimum number of expected columns per row
     * @param parser          function that converts a split CSV row into an instance of {@code T}
     * @param <T>             the target type to map each row to
     * @return a list of parsed records
     * @throws IllegalArgumentException if the file cannot be found, the header is missing,
     *                                  or a row does not meet the expected column count
     * @throws RuntimeException         if an I/O error occurs while reading the file
     */
    public <T> List<T> read(String path, String delimiter, int expectedColumns, Function<String[], T> parser) {
        InputStream is = getClass().getClassLoader().getResourceAsStream(path);

        if (is == null) {
            throw new IllegalArgumentException(path + " not found");
        }

        List<T> records = new ArrayList<>();
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

            String[] headerValues = header.split(Pattern.quote(delimiter));
            validateColumnCount(headerValues, expectedColumns, "header");

            while ((line = br.readLine()) != null) {
                // skip empty lines
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] values = line.split(Pattern.quote(delimiter));

                // check if too few columns
                validateColumnCount(values, expectedColumns, "line");

                try {
                    records.add(parser.apply(values));
                } catch (Exception e) {
                    throw new IllegalArgumentException("Error parsing line: " + Arrays.toString(values), e);
                }
            }

        } catch (IOException e) {
            throw new RuntimeException("Error reading csv data", e);
        }
        return records;
    }

    /**
     * Validates that a CSV row contains at least the expected number of columns.
     *
     * @param values          the split CSV row values
     * @param expectedColumns the minimum number of required columns
     * @param context         description of the validated row (e.g. "header" or "line")
     * @throws IllegalArgumentException if the number of columns is less than expected
     */
    private void validateColumnCount(String[] values, int expectedColumns, String context) {
        if (values.length < expectedColumns) {
            throw new IllegalArgumentException("Invalid CSV " + context + " (expected at least " + expectedColumns + " columns): " + Arrays.toString(values));
        }
    }

}
