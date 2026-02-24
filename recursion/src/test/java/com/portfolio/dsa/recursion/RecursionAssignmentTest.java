package com.portfolio.dsa.recursion;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RecursionAssignmentTest {

    @Test
    void testMultiply() {
        assertEquals(15, RecursionAssignment.multiply(5, 3));
        assertEquals(0, RecursionAssignment.multiply(5, 0));
        assertEquals(5, RecursionAssignment.multiply(5, 1));
    }

    @Test
    void testIsPalindrome() {
        assertTrue(RecursionAssignment.isPalindrome("amanaplanacanalpanama"));
        assertFalse(RecursionAssignment.isPalindrome("test"));
        assertTrue(RecursionAssignment.isPalindrome("a"));
        assertTrue(RecursionAssignment.isPalindrome(""));
    }

    @Test
    void testMoreVowelsOrConsonants() {
        assertEquals("more consonants", RecursionAssignment.moreVowelsOrConsonants("racecar"));
        assertEquals("more vowels", RecursionAssignment.moreVowelsOrConsonants("eager"));
    }

    @Test
    void testTowersOfHanoi() {
        assertDoesNotThrow(() -> RecursionAssignment.towersOfHanoi(4, '1', '2', '3'));
    }
}
