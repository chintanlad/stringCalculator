package com.incubyte.stringcalculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;



/**
 * Unit tests for the StringCalculator class.
 * These tests validate that the add method correctly handles different input scenarios.
 */
public class StringCalculatorTest {

//    private final StringCalculator calculator = new StringCalculator();
//
//    @Test
//    void testEmptyStringReturnsZero() {
//        assertEquals(0, calculator.add(""));
//    }

//    Instance of the StringCalculator for the testing...
    private StringCalculator calculator;

    /**
     * Initialise a new instance of all classes...
     */
    @BeforeEach
    void setup() {
        calculator = new StringCalculator();
    }

    /**
     * An empty string should return 0 as per the defined behavior.
     */
    @Test
    void emptyStringshouldReturnZeroFor() {
        // Should return 0 for empty input
        assertEquals(0, calculator.add(""));
    }

    @Test
    void singleNumberShouldReturnItselfAsSum() {
        // Input: "5" -> Output: 5
        assertEquals(5, calculator.add("5"));
    }

    @Test
    void twoNumberShouldReturnSumOfTwoNumbers() {
        // Input: "7,9" -> Output: 16
        assertEquals(16, calculator.add("7,9"));
    }

    @Test
    void threeNumberShouldReturnSumOfThreeNumbers() {
        // Input: "1,11,111" -> Output: 123
        assertEquals(123, calculator.add("1,11,111"));
    }

    @Test
    void multipleNumberShouldReturnSumOfMultipleNumbers() {
        // Input: "5,15,78,98,69,98" -> Output: 363
        assertEquals(363, calculator.add("5,15,78,98,69,98"));
    }

    @Test
    void newLineBetweenNumbersShouldBeHandledAsDelimiter() {
        // Input: "9\n5,2" -> Output: 16
        assertEquals(16, calculator.add("9\n5,2"));
    }

}
