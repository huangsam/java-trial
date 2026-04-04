package io.huangsam.trial.stdlib.math;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Tests for {@link MathExplorer}.
 * Covers basic operations and defensive error handling.
 */
public class TestMathExplorer {
    private final MathExplorer explorer = new MathExplorer();

    @Test
    void testAdd() {
        assertEquals(2, explorer.add(1, 1));
    }

    @Test
    void testSubtract() {
        assertEquals(3, explorer.subtract(5, 2));
    }

    @Test
    void testMultiply() {
        assertEquals(4, explorer.multiply(2, 2));
    }

    @Test
    void testDivide() {
        assertEquals(5, explorer.divide(40, 8));
    }

    @Test
    void testModulus() {
        assertEquals(6, explorer.modulus(56, 10));
    }

    @Test
    void testDivideByZero() {
        assertThrows(ArithmeticException.class, () -> explorer.divide(10, 0));
    }

    @Test
    void testModulusByZero() {
        assertThrows(ArithmeticException.class, () -> explorer.modulus(10, 0));
    }

    @Test
    void testValidatePositive() {
        explorer.validatePositive(10);
        assertThrows(IllegalArgumentException.class, () -> explorer.validatePositive(0));
        assertThrows(IllegalArgumentException.class, () -> explorer.validatePositive(-5));
    }

    @Test
    void testAddOverflow() {
        // Just checking basic arithmetic, not expecting overflow handling unless specified,
        // but verifying the result is as per JVM rules.
        assertEquals(Integer.MIN_VALUE, explorer.add(Integer.MAX_VALUE, 1));
    }

    @Test
    void testSubtractUnderflow() {
        assertEquals(Integer.MAX_VALUE, explorer.subtract(Integer.MIN_VALUE, 1));
    }
}
