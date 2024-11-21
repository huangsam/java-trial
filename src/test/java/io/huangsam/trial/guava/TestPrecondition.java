package io.huangsam.trial.guava;

import org.junit.jupiter.api.Test;

import static io.huangsam.trial.guava.SimpleValidator.fibonacci;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Testing Precondition primitives in a validation class.
 *
 * @see <a href="https://github.com/google/guava/wiki/PreconditionsExplained">Explanation</a>
 * @see <a href="https://guava.dev/releases/snapshot/api/docs/com/google/common/base/Preconditions.html">API docs</a>
 */
public class TestPrecondition {
    @Test
    void testIllegalArgumentFromPrecondition() {
        assertThrows(IllegalArgumentException.class, () -> fibonacci(-1));
    }
}
