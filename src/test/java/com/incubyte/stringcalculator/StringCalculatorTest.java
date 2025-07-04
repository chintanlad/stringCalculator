package com.incubyte.stringcalculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


/**
 * Unit tests for the StringCalculator class.
 * These tests validate that the add method correctly handles different input scenarios.
 */
public class StringCalculatorTest {

//    Instance of the StringCalculator for the testing...
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

    @Test
    void customDelimiterShouldBeHandledCorrectly() {
        // Input: "//;\n1;2;3" -> Delimiter is ";", Sum is 6
        assertEquals(6, calculator.add("//;\n1;2;3"));
    }

    @Test
    void specialCharacterAsDelimiterShouldSum() {
        // Input: "//$\n4$5$12" -> Delimiter is "$", Sum is 21
        assertEquals(21, calculator.add("//$\n4$5$12"));
    }

    @Test
    void singleNegativeNumberShouldThrowCustomException() {
        NegativeNumberNotAllowedException exception = assertThrows(
                NegativeNumberNotAllowedException.class,
                () -> calculator.add("2,-5,3")
        );

        // Input: "2,-5,3" -> Output: "Negative numbers not allowed: -5"
        assertEquals("Negative numbers not allowed: -5", exception.getMessage());
    }

    @Test
    void multipleNegativeNumbersShouldBeListedInCustomException() {
        NegativeNumberNotAllowedException exception = assertThrows(
                NegativeNumberNotAllowedException.class,
                () -> calculator.add("-1,4,-9,-100")
        );

        // Input: "-1,4,-9,-100" -> Output: "Negative numbers not allowed: -1,-9,-100"
        assertEquals("Negative numbers not allowed: -1,-9,-100", exception.getMessage());
    }

    @Test
    void addMethodShouldTrackCallCount() {
        // Calling add multiple times
        calculator.add("1,2");
        calculator.add("3,4");
        calculator.add("5");

        // Total number of calls should be 3
        assertEquals(3, calculator.getCalledCount());
    }

    @Test
    void numbersGreaterThan1000ShouldBeIgnored() {
        // 1001 is ignored, only 2 is added
        // Input: "2,1001" -> Output: 2
        assertEquals(2, calculator.add("2,1001"));

        // 1000 is included, 1001 is ignored
        // Input: "2,1000,1001" -> Output: 1002
        assertEquals(1002, calculator.add("2,1000,1001"));

        // No numbers below 1000 → returns 0
        // Input: "1001,2000,5000" -> Output: 0
        assertEquals(0, calculator.add("1001,2000,5000"));
    }

    @Test
    void shouldSupportSingleDelimiterOfAnyLength() {
        // Input: "//[***]\n1***2***3" -> Output: 6
        assertEquals(6, calculator.add("//[***]\n1***2***3"));

        // Input: "//[$$$$]\n7$$$$8$$$$9$$$$10" -> Output: 34
        assertEquals(34, calculator.add("//[$$$$]\n7$$$$8$$$$9$$$$10"));
    }

//    @Test
//    void shouldSupportMultipleSingleCharacterDelimiters() {
//        // Input: "//[*][%]\n1*2%3*4" -> Output: 10
//        assertEquals(10, calculator.add("//[*][%]\n1*2%3*4"));
//
//        // Input: "//[&][#]\n25#20#15&10" -> Output: 70
//        assertEquals(70, calculator.add("//[&][#]\n25#20#15&10"));
//    }
//
//    @Test
//    void shouldSupportMultipleMultiCharacterDelimiters() {
//        // Input: "//[**][%%]\n1**2%%3" -> Output: 6
//        assertEquals(6, calculator.add("//[**][%%]\n1**2%%3"));
//
//        // Input: "//[###][&&]\n10###20&&15###8&&9" -> Output: 62
//        assertEquals(62, calculator.add("//[###][&&]\n10###20&&15###8&&9"));
//    }

}
