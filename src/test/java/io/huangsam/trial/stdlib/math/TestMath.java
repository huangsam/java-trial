package io.huangsam.trial.stdlib.math;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Getting a grasp of the JUnit suite from a Java perspective.
 * Trying out the {@code @Test} annotation and static assert
 * methods. The most common one is {@code assertEquals}.
 */
public class TestMath {
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
}
