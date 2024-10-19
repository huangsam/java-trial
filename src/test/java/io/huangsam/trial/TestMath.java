package io.huangsam.trial;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Getting a grasp of the JUnit suite from a Java perspective.
 * Trying out the {@code @Test} annotation and static assert
 * methods. The most common one is {@code assertEquals}.
 */
public class TestMath {
    @Test
    void testAdd() {
        assertEquals(2, 1 + 1);
    }

    @Test
    void testSubtract() {
        assertEquals(3, 5 - 2);
    }

    @Test
    void testMultiply() {
        assertEquals(4, 2 * 2);
    }

    @Test
    void testDivide() {
        assertEquals(5, 40 / 8);
    }

    @Test
    void testModulus() {
        assertEquals(6, 56 % 10);
    }
}
