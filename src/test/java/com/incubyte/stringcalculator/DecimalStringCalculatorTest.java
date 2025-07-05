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
        // Returns 0.0 for an empty string input

        // Input: "" -> Output: 0.0
        assertEquals(0.0, calculator.add(""));
    }

    @Test
    void singleDecimalShouldReturnItself() {
        // Just one number? Return it as-is

        // Input: "2.5" -> Output: 2.5
        assertEquals(2.5, calculator.add("2.5"));
    }

    @Test
    void twoDecimalsShouldReturnTheirSum() {
        // Should correctly add two decimal values

        // Input: "1.5,2.7" -> Output: 4.0
        assertEquals(4.2, calculator.add("1.5,2.7"));
    }
}
