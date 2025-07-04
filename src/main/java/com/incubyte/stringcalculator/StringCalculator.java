package com.incubyte.stringcalculator;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * A simple calculator that adds numbers in different formats.
 * Supports:
 * - Comma and newline as delimiters
 * - Custom single-character delimiters with the format: "//[delimiter]\n[numbers]"
 * - Single Negative numbers show the exception message
 * - Multiple negative numbers show the exception message
 * - Count how many times the function is called
 */
public class StringCalculator {

    // Counter to track how many times the 'add' method has been called
    private int callCount = 0;

    public int add(String input) {

        callCount++; // Track method calls

        // Return 0 for empty input
        if (input.isEmpty()) return 0;

        // Set default delimiters (comma or newline)
        String delimiterPattern = ",|\n";
        String numbersOnly = input;

        // Check if the input defines a custom delimiter (e.g., "//;\n1;2")
        if (input.startsWith("//")) {
            int delimiterEndIndex = input.indexOf("\n");
            String customDelimiter = input.substring(2, delimiterEndIndex);
            delimiterPattern = Pattern.quote(customDelimiter);
            numbersOnly = input.substring(delimiterEndIndex + 1);
        }

        // Split numbers using the detected delimiter
        String[] tokens = numbersOnly.split(delimiterPattern);
        List<Integer> negativeNumbers = new ArrayList<>();
        int total = 0;

        for (String token : tokens) {
            int number = Integer.parseInt(token.trim());

            if (number < 0) {
                negativeNumbers.add(number);
            }

            total += number;
        }

        // Throw an exception if any negative numbers are found
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
