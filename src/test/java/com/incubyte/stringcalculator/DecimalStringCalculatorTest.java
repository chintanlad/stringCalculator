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

    @Test
    void decimalsSeparatedByNewlineShouldBeSummed() {
        // Supports newline as a valid delimiter between decimals

        // Input: "3.2\n1.8" -> Output: 5.0
        assertEquals(5.0, calculator.add("3.2\n1.8"));
    }

    @Test
    void customDelimiterShouldBeHandledForDecimalInput() {
        // Handles custom single-character delimiter (e.g., ";")

        // Input: "//;\n1.5;2.5" -> Output: 4.0
        assertEquals(4.0, calculator.add("//;\n1.5;2.5"));
    }

    @Test
    void specialCharacterDelimiterWithDecimalNumbers() {
        // Supports special characters as custom delimiters (like "$")

        // Input: "//$\n4.25$5.75$10" -> Output: 20.0
        assertEquals(20.0, calculator.add("//$\n4.25$5.75$10"));
    }

    @Test
    void negativeDecimalShouldThrowException() {
        // A single negative number should trigger an exception with the number in the message

        // Input: "-2.5,3.5" should throw exception with message
        DoubleNegativeNumberException exception = assertThrows(
                DoubleNegativeNumberException.class,
                () -> calculator.add("-2.5,3.5")
        );

        assertEquals("Negative numbers not allowed: -2.5", exception.getMessage());
    }

    @Test
    void multipleNegativeDecimalsShouldBeListedInException() {
        // All negative decimals should be listed in the exception message

        // Input: "-1.1,-2.2,3.3" -> Exception lists all negatives
        DoubleNegativeNumberException exception = assertThrows(
                DoubleNegativeNumberException.class,
                () -> calculator.add("-1.1,-2.2,3.3")
        );

        assertEquals("Negative numbers not allowed: -1.1,-2.2", exception.getMessage());
    }

    @Test
    void decimalNumbersAbove1000ShouldBeIgnored() {
        // Values greater than 1000 should be skipped during summation

        // Input: "2.2,1000.5" -> Output: 2.2
        assertEquals(2.2, calculator.add("2.2,1000.5"));
    }

    @Test
    void shouldSupportMultipleMultiCharDelimitersWithDecimals() {
        // Handles multiple custom delimiters with more than one character

        // Input: "//[**][%%]\n1.1**2.2%%3.3" -> Output: 6.6
        assertEquals(6.6, calculator.add("//[**][%%]\n1.1**2.2%%3.3"));
    }

    @Test
    void shouldSupportMultipleCallsTracking() {
        // Tracks how many times the add method has been called

        calculator.add("1.1,2.2");
        calculator.add("3.3");
        calculator.add("4.4,5.5");

        // Total calls = 3
        assertEquals(3, calculator.getCalledCount());
    }
}
