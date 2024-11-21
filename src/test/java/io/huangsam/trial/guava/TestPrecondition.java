package io.huangsam.trial.guava;

import org.junit.jupiter.api.Test;

import static io.huangsam.trial.guava.SimpleValidator.salute;
import static io.huangsam.trial.guava.SimpleValidator.fibonacci;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Testing Precondition primitives in a validation class.
 *
 * @see <a href="https://github.com/google/guava/wiki/PreconditionsExplained">Explanation</a>
 * @see <a href="https://guava.dev/releases/snapshot/api/docs/com/google/common/base/Preconditions.html">API docs</a>
 */
public class TestPrecondition {
    @Test
    void testIllegalArgumentFromFibonacciPrecondition() {
        assertThrows(IllegalArgumentException.class, () -> fibonacci(-1));
        assertThrows(IllegalArgumentException.class, () -> fibonacci(7));
    }

    @Test
    void testGoodFibonacciPrecondition() {
        assertEquals(3, fibonacci(3));
        assertEquals(5, fibonacci(4));
        assertEquals(8, fibonacci(5));
        assertEquals(13, fibonacci(6));
    }

    @Test
    void testSaluteNull() {
        assertThrows(NullPointerException.class, () -> salute(null));
    }

    @Test
    void testSaluteWorld() {
        assertEquals("Hello world", salute("world"));
    }
}
