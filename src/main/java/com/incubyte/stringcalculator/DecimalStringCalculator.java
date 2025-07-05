package com.incubyte.stringcalculator;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A calculator that supports addition of decimal numbers from specially formatted input strings.
 * Supports:
 * - Comma and newline as delimiters
 * - Custom single or multi-character delimiters with format: "//[delimiter]\n[numbers]"
 * - Handling multiple custom delimiters
 * - Ignoring numbers greater than 1000
 * - Throwing exception on negative decimal numbers
 */
public class DecimalStringCalculator {

    // Counter to keep track of how many times the add method is invoked
    private int callCount = 0;

    /**
     * Parses the input string to extract decimal numbers and returns their sum.
     * Custom and multiple delimiters are supported. Ignores numbers > 1000.
     * Throws exception if negative numbers are found.
     *
     * @param input the input string containing decimal numbers
     * @return the sum of valid decimal numbers
     */
    public double add(String input) {

        callCount++; // Track number of calls to add()

        // Return 0.0 if the input is null or empty
        if (input == null || input.isEmpty()) return -1;

        // Default delimiters are comma and newline
        String delimiterPattern = ",|\n";
        String numbersOnly = input;

        // Check if the input starts with custom delimiter syntax
        if (input.startsWith("//")) {
            int delimiterEndIndex = input.indexOf("\n");

            // Extract the custom delimiter section
            String customDelimiter = input.substring(2, delimiterEndIndex);
            numbersOnly = input.substring(delimiterEndIndex + 1);

            // Support for multiple delimiters in brackets
            Matcher matcher = Pattern.compile("\\[(.*?)]").matcher(customDelimiter);
            List<String> delimiters = new ArrayList<>();

            while (matcher.find()) {
                delimiters.add(Pattern.quote(matcher.group(1)));
            }

            // If no brackets found, treat it as a single delimiter
            if (delimiters.isEmpty()) {
                delimiterPattern = Pattern.quote(customDelimiter);
            } else {
                delimiterPattern = String.join("|", delimiters);
            }
        }

        // Split the numeric portion using the compiled delimiter pattern
        String[] tokens = numbersOnly.split(delimiterPattern);
        List<Double> negativeNumbers = new ArrayList<>();
        double total = 0.0;

        // Process each token and accumulate the valid decimal values
        for (String token : tokens) {
            if (token.isBlank()) continue;

            double number = Double.parseDouble(token.trim());

            if (number < 0) {
                negativeNumbers.add(number);
            } else if (number <= 1000) {
                total += number;
            }
        }

        // Raise an exception if negative decimal numbers are encountered
        if (!negativeNumbers.isEmpty()) {
            throw new DoubleNegativeNumberException(negativeNumbers);
        }

        return total;
    }

    /**
     * Returns the number of times the add method has been called.
     *
     * @return total number of invocations
     */
    public int getCalledCount() {
        return callCount;
    }
}
