package org.huangsam.sample;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

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
