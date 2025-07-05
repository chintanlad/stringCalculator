package com.incubyte.stringcalculator;


import java.util.List;
import java.util.stream.Collectors;

/**
 * Exception thrown when one or more negative numbers are encountered in the input.
 */
public class DoubleNegativeNumberException extends RuntimeException {

    public DoubleNegativeNumberException(List<Double> negativeNumbers) {
        super(buildMessage(negativeNumbers));
    }

    /**
     * Builds the error message by converting the list of negative numbers into a comma-separated string.
     *
     * @param negatives list of negative integers
     * @return formatted exception message including the invalid numbers
     */
    private static String buildMessage(List<Double> negatives) {
        String negativeList = negatives.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(","));
        return "Negative numbers not allowed: " + negativeList;
    }
}