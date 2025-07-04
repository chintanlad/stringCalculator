package com.incubyte.stringcalculator;


import java.util.regex.Pattern;

/**
 * A simple calculator that adds numbers in different formats.
 * Supports:
 * - Comma and newline as delimiters
 * - Custom single-character delimiters with the format: "//[delimiter]\n[numbers]"
 */
public class StringCalculator {

    public int add(String numbers) {
        // Return 0 if the input string is empty
        if (numbers.isEmpty()) return 0;

        String delimiter = ",|\n"; // Default delimiters: comma or newline
        String actualNumbers = numbers;

        // Check for custom delimiter syntax at the start of the string
        if (numbers.startsWith("//")) {
            int delimiterEndIndex = numbers.indexOf("\n");
            delimiter = Pattern.quote(numbers.substring(2, delimiterEndIndex)); // escape special characters
            actualNumbers = numbers.substring(delimiterEndIndex + 1);
        }

        // Splite the string by the delemiter and create the individuals numbers
        String[] nums = actualNumbers.split(delimiter);
        int sum = 0;
        for (String num : nums) {
            sum += Integer.parseInt(num);
        }
        return sum;
    }
}
