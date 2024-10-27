package io.huangsam.trial.guava;

import com.google.common.math.LongMath;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Google Guava is a useful set of utilities and data structures.
 */
public class TestOtherMath {
    @Test
    void testSomeLongMath() {
        assertEquals(120L, LongMath.factorial(5));
        assertTrue(LongMath.isPowerOfTwo(16L));
    }
}
