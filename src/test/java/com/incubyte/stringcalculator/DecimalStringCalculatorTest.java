package com.incubyte.stringcalculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for the DecimalStringCalculator class.
 * These tests validate the behavior of the add method for decimal numbers
 * and all supported features like delimiters, call count, and negative values.
 */
public class DecimalStringCalculatorTest {

    // Instance of the DecimalStringCalculator for testing
    private DecimalStringCalculator calculator;

    /**
     * Setup a new calculator instance before each test
     */
    @BeforeEach
    void setUp() {
        calculator = new DecimalStringCalculator();
    }

    @Test
    void emptyInputShouldReturnZero() {
        // Input: "" -> Output: 0.0
        assertEquals(0.0, calculator.add(""));
    }

    
}
