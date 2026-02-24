package com.portfolio.dsa.recursion;

public class RecursionAssignment {

    // Recursive function to multiply two positive integers using only addition
    public static int multiply(int m, int n) {
        if (n == 0) { // if n is zero, the result is zero
            return 0;
        }
        return m + multiply(m, n - 1); // Recursive step: add m to the result of multiply(m, n - 1)
    }

    // Recursive function to check if a string is a palindrome
    public static boolean isPalindrome(String s) {
        if (s.length() <= 1) { // A string of length 0 or 1 is always a palindrome
            return true;
        }
        if (s.charAt(0) != s.charAt(s.length() - 1)) { // If first and last characters don't match, it's not a
                                                       // palindrome
            return false;
        }
        // Recursive step to check the substring excluding the first and last characters
        return isPalindrome(s.substring(1, s.length() - 1));
    }

    // Function to determine whether a string has more vowels or consonants
    public static String moreVowelsOrConsonants(String s) {
        int vowels = countVowels(s, 0); // calls this to Count vowels in the string
        int consonants = countConsonants(s, 0); // call this to Count consonants in the string
        if (vowels > consonants) { // Compare the counts
            return "more vowels";
        } else {
            return "more consonants";
        }
    }

    // Recursive function to count vowels in a string
    private static int countVowels(String s, int index) {
        if (index == s.length()) { // Base case: if index reaches the string length, return 0
            return 0;
        }
        char c = Character.toLowerCase(s.charAt(index)); // Convert character to lowercase
        int count = ("aeiou".indexOf(c) >= 0) ? 1 : 0; // Check if character is a vowel
        return count + countVowels(s, index + 1); // Recursive call with next character index
    }

    // Recursive function to count consonants in a string
    private static int countConsonants(String s, int index) {
        if (index == s.length()) { // Base case: if index reaches the string length, return 0
            return 0;
        }
        char c = Character.toLowerCase(s.charAt(index)); // Convert character to lowercase
        int count = (Character.isLetter(c) && "aeiou".indexOf(c) == -1) ? 1 : 0; // Check if character is a consonant
        return count + countConsonants(s, index + 1); // Recursive call with next character index
    }

    // Recursive function to solve the Towers of Hanoi problem
    public static void towersOfHanoi(int n, char source, char auxiliary, char destination) {
        if (n == 1) { // if there's only one disk, move it directly
            System.out.println("Move disc from peg '" + source + "' to peg '" + destination + "'");
            return;
        }
        // Move n-1 disks from source to auxiliary, using destination as temporary
        // storage
        towersOfHanoi(n - 1, source, destination, auxiliary);
        // Move the nth disk from source to destination
        System.out.println("Move disc from peg '" + source + "' to peg '" + destination + "'");
        // Move the n-1 disks from auxiliary to destination, using source as temporary
        // storage
        towersOfHanoi(n - 1, auxiliary, source, destination);
    }

}
