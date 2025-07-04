package com.incubyte.stringcalculator;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Exception thrown when one or more negative numbers are encountered in the input.
 */
public class NegativeNumberNotAllowedException extends RuntimeException {

    public NegativeNumberNotAllowedException(List<Integer> negativeNumbers) {
        super(buildMessage(negativeNumbers));
    }

    private static String buildMessage(List<Integer> negatives) {
        String negativeList = negatives.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(","));
        return "Negative numbers not allowed: " + negativeList;
    }
}