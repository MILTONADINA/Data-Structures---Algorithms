package com.portfolio.dsa.complexity;

public class ComplexityAssignment {

    // Algorithm 1
    public static void algorithm1(int n) {
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += i;
        }
    }

    // Algorithm 2
    public static void algorithm2(int n) {
        int sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sum += i + j;
            }
        }
    }

    // Algorithm 3
    public static void algorithm3(int n) {
        int sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n * n; j++) {
                sum += i + j;
            }
        }
    }

    // Algorithm 4
    public static void algorithm4(int n) {
        int sum = 0;
        if (n <= 0)
            return;
        for (int i = 1; i < n; i *= 2) {
            sum += i;
        }
    }

    // Algorithm 5
    public static void algorithm5(int n) {
        if (n <= 1)
            return;
        algorithm5(n - 1);
        algorithm5(n - 1);
    }

    // measure time
    public static long measureTime(Runnable algorithm) {
        long startTime = System.nanoTime();
        algorithm.run();
        long stopTime = System.nanoTime();
        return stopTime - startTime;
    }

}
