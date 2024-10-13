package org.huangsam.sample;

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
        assertEquals(1 + 1, 2);
    }

    @Test
    void testMultiply() {
        assertEquals(2 * 2, 4);
    }
}
