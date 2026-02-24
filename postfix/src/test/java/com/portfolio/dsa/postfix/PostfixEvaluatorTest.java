package com.portfolio.dsa.postfix;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PostfixEvaluatorTest {

    @Test
    void testSimpleAddition() {
        assertEquals(5, PostfixEvaluator.evaluate("2 3 +"));
    }

    @Test
    void testComplexExpression() {
        // (3 + 4) * 2 / 7 = 2
        assertEquals(2, PostfixEvaluator.evaluate("3 4 + 2 * 7 /"));
    }

    @Test
    void testDivisionByZeroThrowsException() {
        assertThrows(ArithmeticException.class, () -> {
            PostfixEvaluator.evaluate("5 0 /");
        });
    }

    @Test
    void testNotEnoughOperandsThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            PostfixEvaluator.evaluate("5 +");
        });
    }

    @Test
    void testTooManyOperandsThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            PostfixEvaluator.evaluate("5 5 5 +");
        });
    }

    @Test
    void testInvalidOperatorThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            PostfixEvaluator.evaluate("5 5 a");
        });
    }
}
