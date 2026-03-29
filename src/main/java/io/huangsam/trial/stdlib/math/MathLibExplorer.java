package io.huangsam.trial.stdlib.math;

/**
 * Demonstrates the use of the standard java.lang.Math library for more advanced math operations.
 */
public class MathLibExplorer {

    /**
     * Returns the absolute value of a double.
     *
     * @param value the value to process
     * @return the absolute value
     */
    public double absolute(double value) {
        return Math.abs(value);
    }

    /**
     * Returns the value of the first argument raised to the power of the second argument.
     *
     * @param base     the base
     * @param exponent the exponent
     * @return the result of base^exponent
     */
    public double power(double base, double exponent) {
        return Math.pow(base, exponent);
    }

    /**
     * Returns the correctly rounded positive square root of a double value.
     *
     * @param value the value to process
     * @return the square root
     */
    public double squareRoot(double value) {
        return Math.sqrt(value);
    }

    /**
     * Returns the greater of two int values.
     *
     * @param a first value
     * @param b second value
     * @return the larger of a and b
     */
    public int maximum(int a, int b) {
        return Math.max(a, b);
    }

    /**
     * Returns the smaller of two int values.
     *
     * @param a first value
     * @param b second value
     * @return the smaller of a and b
     */
    public int minimum(int a, int b) {
        return Math.min(a, b);
    }

    /**
     * Returns the closest long to the argument, with ties rounding to positive infinity.
     *
     * @param value the value to round
     * @return the rounded long
     */
    public long roundToLong(double value) {
        return Math.round(value);
    }

    /**
     * Returns the smallest double value that is greater than or equal to the argument and is equal to a mathematical integer.
     *
     * @param value the value to process
     * @return the ceiling value
     */
    public double ceilToDouble(double value) {
        return Math.ceil(value);
    }

    /**
     * Returns the largest double value that is less than or equal to the argument and is equal to a mathematical integer.
     *
     * @param value the value to process
     * @return the floor value
     */
    public double floorToDouble(double value) {
        return Math.floor(value);
    }
}
