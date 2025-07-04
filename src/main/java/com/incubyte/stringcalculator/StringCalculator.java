package com.incubyte.stringcalculator;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A simple calculator that adds numbers in different formats.
 * Supports:
 * - Comma and newline as delimiters
 * - Custom single-character delimiters with the format: "//[delimiter]\n[numbers]"
 * - Single Negative numbers show the exception message
 * - Multiple negative numbers show the exception message
 * - Count how many times the function is called
 * - Number greater than 1000 should be ignored
 * - Delimiters can be of any length
 */
public class StringCalculator {

    // Counter to track how many times the 'add' method has been called
    private int callCount = 0;

    public int add(String input) {

        callCount++; // Increment the count each time the method is called

        // Return 0 if the input is empty or null
        if (input == null || input.isEmpty()) return 0;

        // Default delimiters are comma (,) or newline (\n)
        String delimiterPattern = ",|\n";
        String numbersOnly = input;

        // Check if a custom delimiter is defined at the beginning of the input
        if (input.startsWith("//")) {
            int delimiterEndIndex = input.indexOf("\n");

            // Extract custom delimiter (handles both single-char and multi-char inside brackets)
            String customDelimiter = input.substring(2, delimiterEndIndex);
            numbersOnly = input.substring(delimiterEndIndex + 1);

            // If multiple or multi-character delimiters are used, extract them from square brackets
            Matcher matcher = Pattern.compile("\\[(.*?)]").matcher(customDelimiter);
            List<String> delimiters = new ArrayList<>();

            while (matcher.find()) {
                delimiters.add(Pattern.quote(matcher.group(1)));
            }

            // Fallback to single delimiter if no brackets found
            if (delimiters.isEmpty()) {
                delimiterPattern = Pattern.quote(customDelimiter);
            } else {
                delimiterPattern = String.join("|", delimiters);
            }
        }

        // Split the number string using the resolved delimiter(s)
        String[] tokens = numbersOnly.split(delimiterPattern);
        List<Integer> negativeNumbers = new ArrayList<>();
        int total = 0;

        // Process each token to calculate the sum
        for (String token : tokens) {
            if (token.isBlank()) continue;

            int number = Integer.parseInt(token.trim());

            // Collect negative numbers for error reporting
            if (number < 0) {
                negativeNumbers.add(number);
            }
            // Ignore numbers greater than 1000 as per the requirement
            else if (number <= 1000) {
                total += number;
            }
        }

        // If any negative numbers are found, raise a custom exception
        if (!negativeNumbers.isEmpty()) {
            throw new NegativeNumberNotAllowedException(negativeNumbers);
        }

        return total;
    }


    /**
     * Returns how many times the add method was invoked.
     */
    public int getCalledCount() {
        return callCount;
    }
}
