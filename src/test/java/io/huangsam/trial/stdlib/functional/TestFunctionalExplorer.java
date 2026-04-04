package io.huangsam.trial.stdlib.functional;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for {@link FunctionalExplorer}.
 */
public class TestFunctionalExplorer {

    private final FunctionalExplorer explorer = new FunctionalExplorer();

    @Test
    void testStringTransformer() {
        String result = explorer.useTransformer("hello", String::toUpperCase);
        assertEquals("HELLO", result);

        String result2 = explorer.useTransformer("world", s -> s + "!");
        assertEquals("world!", result2);
    }

    @Test
    void testCheckCondition() {
        assertTrue(explorer.checkCondition("test", s -> s.length() > 2));
        assertFalse(explorer.checkCondition(10, i -> i < 5));
    }

    @Test
    void testMapValue() {
        Integer result = explorer.mapValue("123", Integer::parseInt);
        assertEquals(123, result);

        String result2 = explorer.mapValue(100, String::valueOf);
        assertEquals("100", result2);
    }

    @Test
    void testCombine() {
        String result = explorer.combine("a", "b", (String s1, String s2) -> s1 + s2);
        assertEquals("ab", result);

        Integer result2 = explorer.combine(10, 20, Integer::sum);
        assertEquals(30, (int) result2);
    }
}
