package io.huangsam.trial.stdlib.math;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class TestMathLib {

    private final MathLibExplorer explorer = new MathLibExplorer();

    @Test
    void testAbsolute() {
        assertEquals(5.0, explorer.absolute(-5.0));
        assertEquals(5.0, explorer.absolute(5.0));
    }

    @Test
    void testPower() {
        assertEquals(8.0, explorer.power(2.0, 3.0));
        assertEquals(1.0, explorer.power(5.0, 0.0));
    }

    @Test
    void testSquareRoot() {
        assertEquals(3.0, explorer.squareRoot(9.0));
        assertEquals(4.0, explorer.squareRoot(16.0));
        assertEquals(Double.NaN, explorer.squareRoot(-1.0));
    }

    @Test
    void testMaximum() {
        assertEquals(10, explorer.maximum(5, 10));
        assertEquals(10, explorer.maximum(10, 5));
    }

    @Test
    void testMinimum() {
        assertEquals(5, explorer.minimum(5, 10));
        assertEquals(5, explorer.minimum(10, 5));
    }

    @Test
    void testRoundToLong() {
        assertEquals(5L, explorer.roundToLong(4.6));
        assertEquals(4L, explorer.roundToLong(4.4));
    }

    @Test
    void testCeilToDouble() {
        assertEquals(5.0, explorer.ceilToDouble(4.1));
        assertEquals(4.0, explorer.ceilToDouble(4.0));
    }

    @Test
    void testFloorToDouble() {
        assertEquals(4.0, explorer.floorToDouble(4.9));
        assertEquals(4.0, explorer.floorToDouble(4.0));
    }

    @Test
    void testSpecialDoubleValues() {
        assertEquals(Double.NaN, explorer.absolute(Double.NaN));
        assertEquals(Double.POSITIVE_INFINITY, explorer.absolute(Double.NEGATIVE_INFINITY));
        assertTrue(Double.isNaN(explorer.power(-1.0, 0.5)));
    }
}
