package io.huangsam.trial.concurrent.collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Tests for {@link ConcurrentMapExplorer}.
 */
public class TestConcurrentMapExplorer {

    private ConcurrentMapExplorer explorer;

    @BeforeEach
    void setUp() {
        explorer = new ConcurrentMapExplorer();
    }

    @Test
    void testPutIfAbsent() {
        assertNull(explorer.putIfAbsent("key1", 100));
        assertEquals(100, explorer.putIfAbsent("key1", 200));
    }

    @Test
    void testCompute() {
        assertEquals(10, explorer.increment("key1", 10));
        assertEquals(30, explorer.increment("key1", 20));
    }

    @Test
    void testClearAndSize() {
        explorer.increment("key1", 1);
        explorer.increment("key2", 2);
        assertEquals(2, explorer.size());
        explorer.clear();
        assertEquals(0, explorer.size());
    }
}
