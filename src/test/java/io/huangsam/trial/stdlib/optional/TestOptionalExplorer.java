package io.huangsam.trial.stdlib.optional;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for {@link OptionalExplorer}.
 */
public class TestOptionalExplorer {

    private final OptionalExplorer explorer = new OptionalExplorer();

    @Test
    void testProcessIfPresentOrElse() {
        assertTrue(explorer.processIfPresentOrElse(Optional.of("value")));
        assertFalse(explorer.processIfPresentOrElse(Optional.empty()));
    }

    @Test
    void testWithDefault() {
        assertEquals("value", explorer.withDefault(Optional.of("value")).get());
        assertEquals("DEFAULT", explorer.withDefault(Optional.empty()).get());
    }

    @Test
    void testTransformToInt() {
        assertEquals(123, explorer.transformToInt(Optional.of("123")).get());
        assertTrue(explorer.transformToInt(Optional.empty()).isEmpty());
        assertTrue(explorer.transformToInt(Optional.of("abc")).isEmpty());
    }

    @Test
    void testGetOrThrow() {
        assertEquals("value", explorer.getOrThrow(Optional.of("value")));
        assertThrows(IllegalStateException.class, () -> explorer.getOrThrow(Optional.empty()));
    }
}
