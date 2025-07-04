package com.incubyte.stringcalculator;


/**
 * A simple calculator that adds numbers in the different formats
 */
public class StringCalculator {

    public int add(String numbers) {
        // Return 0 if the input string is empty
        if (numbers.isEmpty()) return 0;

        // Replace newline characters with commas to unify delimiters
        String normalized = numbers.replace("\n", ",");

        // Split the normalized string by commas
        String[] nums = normalized.split(",");

        int sum = 0;

        // Convert each string to an integer and add to the total sum
        for (String num : nums) {
            sum += Integer.parseInt(num);
        }

        return sum;
    }
}
