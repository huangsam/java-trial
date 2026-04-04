package io.huangsam.trial.stdlib.math;

/**
 * Math utilities for basic operations with defensive checks.
 */
public class MathExplorer {
    /**
     * Constructs a math explorer.
     */
    public MathExplorer() {
        // Default constructor
    }

    /**
     * Adds two integers.
     *
     * @param a the first integer
     * @param b the second integer
     * @return the sum of a and b
     */
    public int add(int a, int b) {
        return a + b;
    }

    /**
     * Subtracts the second integer from the first.
     *
     * @param a the first integer
     * @param b the second integer
     * @return the difference of a and b
     */
    public int subtract(int a, int b) {
        return a - b;
    }

    /**
     * Multiplies two integers.
     *
     * @param a the first integer
     * @param b the second integer
     * @return the product of a and b
     */
    public int multiply(int a, int b) {
        return a * b;
    }

    /**
     * Divides the first integer by the second.
     *
     * @param a the dividend
     * @param b the divisor
     * @return the quotient
     * @throws ArithmeticException if b is zero
     */
    public int divide(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Cannot divide by zero");
        }
        return a / b;
    }

    /**
     * Computes the remainder of dividing the first integer by the second.
     *
     * @param a the dividend
     * @param b the divisor
     * @return the remainder
     * @throws ArithmeticException if b is zero
     */
    public int modulus(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Cannot compute modulus with zero divisor");
        }
        return a % b;
    }

    /**
     * Validates if a number is positive.
     *
     * @param number the number to validate
     * @throws IllegalArgumentException if the number is not positive
     */
    public void validatePositive(int number) {
        if (number <= 0) {
            throw new IllegalArgumentException("Number must be positive: " + number);
        }
    }
}
