package com.incubyte.stringcalculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


/**
 * Unit tests for the StringCalculator class.
 * These tests verify the correct behavior of the add method
 * under various input scenarios including delimiters, invalid inputs, and custom rules.
 */
public class StringCalculatorTest {

    // Instance of the StringCalculator for the testing...
    private StringCalculator calculator;

    /**
     * Initialise a new instance of all classes...
     */
    @BeforeEach
    void setup() {
        calculator = new StringCalculator();
    }

    @Test
    void emptyStringshouldReturnZeroFor() {
        // Should return 0 for empty input
        assertEquals(0, calculator.add(""));
    }

    @Test
    void singleNumberShouldReturnItselfAsSum() {
        // If the string contains only one number, return that number

        // Input: "5" -> Output: 5
        assertEquals(5, calculator.add("5"));
    }

    @Test
    void twoNumberShouldReturnSumOfTwoNumbers() {
        // Sum of two comma-separated numbers

        // Input: "7,9" -> Output: 16
        assertEquals(16, calculator.add("7,9"));
    }

    @Test
    void threeNumberShouldReturnSumOfThreeNumbers() {
        // Add three comma-separated numbers

        // Input: "1,11,111" -> Output: 123
        assertEquals(123, calculator.add("1,11,111"));
    }

    @Test
    void multipleNumberShouldReturnSumOfMultipleNumbers() {
        // Add multiple comma-separated values

        // Input: "5,15,78,98,69,98" -> Output: 363
        assertEquals(363, calculator.add("5,15,78,98,69,98"));
    }

    @Test
    void newLineBetweenNumbersShouldBeHandledAsDelimiter() {
        // Newline characters should work like commas as delimiters

        // Input: "9\n5,2" -> Output: 16
        assertEquals(16, calculator.add("9\n5,2"));
    }

    @Test
    void customDelimiterShouldBeHandledCorrectly() {
        // Custom delimiter ";" should be used for splitting

        // Input: "//;\n1;2;3" -> Delimiter is ";", Sum is 6
        assertEquals(6, calculator.add("//;\n1;2;3"));
    }

    @Test
    void specialCharacterAsDelimiterShouldSum() {
        // Custom delimiter with a special character ('$')

        // Input: "//$\n4$5$12" -> Delimiter is "$", Sum is 21
        assertEquals(21, calculator.add("//$\n4$5$12"));
    }

    @Test
    void singleNegativeNumberShouldThrowCustomException() {
        // Negative numbers should throw a custom exception with the number in the message

        NegativeNumberNotAllowedException exception = assertThrows(
                NegativeNumberNotAllowedException.class,
                () -> calculator.add("2,-5,3")
        );

        // Input: "2,-5,3" -> Output: "Negative numbers not allowed: -5"
        assertEquals("Negative numbers not allowed: -5", exception.getMessage());
    }

    @Test
    void multipleNegativeNumbersShouldBeListedInCustomException() {
        // All negative numbers should be listed in the exception message

        NegativeNumberNotAllowedException exception = assertThrows(
                NegativeNumberNotAllowedException.class,
                () -> calculator.add("-1,4,-9,-100")
        );

        // Input: "-1,4,-9,-100" -> Output: "Negative numbers not allowed: -1,-9,-100"
        assertEquals("Negative numbers not allowed: -1,-9,-100", exception.getMessage());
    }

    @Test
    void addMethodShouldTrackCallCount() {
        // Verifies if the add method correctly tracks how many times it's been called

        // Calling add multiple times
        calculator.add("1,2");
        calculator.add("3,4");
        calculator.add("5");

        // Total number of calls should be 3
        assertEquals(3, calculator.getCalledCount());
    }

    @Test
    void numbersGreaterThan1000ShouldBeIgnored() {
        // Numbers > 1000 should be ignored during addition

        // Input: "2,1001" -> Output: 2
        assertEquals(2, calculator.add("2,1001"));

        // Input: "2,1000,1001" -> Output: 1002
        assertEquals(1002, calculator.add("2,1000,1001"));

        // Input: "1001,2000,5000" -> Output: 0
        assertEquals(0, calculator.add("1001,2000,5000"));
    }

    @Test
    void shouldSupportSingleDelimiterOfAnyLength() {
        // Custom delimiter of more than one character

        // Input: "//[***]\n1***2***3" -> Output: 6
        assertEquals(6, calculator.add("//[***]\n1***2***3"));

        // Input: "//[$$$$]\n7$$$$8$$$$9$$$$10" -> Output: 34
        assertEquals(34, calculator.add("//[$$$$]\n7$$$$8$$$$9$$$$10"));
    }

    @Test
    void shouldSupportMultipleSingleCharacterDelimiters() {
        // Multiple single-character delimiters should work together

        // Input: "//[*][%]\n1*2%3*4" -> Output: 10
        assertEquals(10, calculator.add("//[*][%]\n1*2%3*4"));

        // Input: "//[&][#]\n25#20#15&10" -> Output: 70
        assertEquals(70, calculator.add("//[&][#]\n25#20#15&10"));
    }

    @Test
    void shouldSupportMultipleMultiCharacterDelimiters() {
        // Multiple delimiters with more than one character each

        // Input: "//[**][%%]\n1**2%%3" -> Output: 6
        assertEquals(6, calculator.add("//[**][%%]\n1**2%%3"));

        // Input: "//[###][&&]\n10###20&&15###8&&9" -> Output: 62
        assertEquals(62, calculator.add("//[###][&&]\n10###20&&15###8&&9"));
    }
}
