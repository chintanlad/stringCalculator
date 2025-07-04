package com.incubyte.stringcalculator;


/**
 * A simple calculator that adds numbers in the different formats
 */
public class StringCalculator {

    public int add(String numbers) {

        // Return 0 if the input string is null
        if (numbers.isEmpty()) return 0;

        // Split the input string by commas to extract individual number strings
        String[] nums = numbers.split(",");

        int sum = 0;

        // Convert each string to an integer and add to the total sum
        for (String num : nums) {
            sum += Integer.parseInt(num);
        }
        return sum;
    }
}
