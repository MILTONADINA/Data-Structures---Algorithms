package com.portfolio.dsa.complexity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ComplexityAssignmentTest {

    @Test
    void testAlgorithm1() {
        assertDoesNotThrow(() -> ComplexityAssignment.algorithm1(10));
    }

    @Test
    void testAlgorithm2() {
        assertDoesNotThrow(() -> ComplexityAssignment.algorithm2(10));
    }

    @Test
    void testAlgorithm3() {
        assertDoesNotThrow(() -> ComplexityAssignment.algorithm3(10));
    }

    @Test
    void testAlgorithm4() {
        assertDoesNotThrow(() -> ComplexityAssignment.algorithm4(10));
    }

    @Test
    void testAlgorithm5() {
        assertDoesNotThrow(() -> ComplexityAssignment.algorithm5(10));
    }
}
