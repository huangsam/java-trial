package io.huangsam.trial.concurrent.patterns;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for {@link AsyncExplorer}.
 */
public class TestAsyncExplorer {

    private AsyncExplorer explorer;

    @BeforeEach
    void setUp() {
        explorer = new AsyncExplorer();
    }

    @AfterEach
    void tearDown() {
        explorer.shutdown();
    }

    @Test
    void testSupplyAsync() {
        CompletableFuture<String> future = explorer.supplyAsync("hello");
        assertEquals("HELLO", future.join());
    }

    @Test
    void testComposeAsync() {
        CompletableFuture<String> future = explorer.composeAsync("first", "second");
        assertEquals("FIRSTSECOND", future.join());
    }

    @Test
    void testCombineAsync() {
        CompletableFuture<String> future = explorer.combineAsync("apple", "banana");
        assertEquals("APPLE BANANA", future.join());
    }

    @Test
    void testAllOfAsync() {
        List<String> values = List.of("one", "two", "three");
        CompletableFuture<List<String>> future = explorer.allOfAsync(values);
        List<String> result = future.join();
        assertEquals(3, result.size());
        assertTrue(result.contains("ONE"));
        assertTrue(result.contains("TWO"));
        assertTrue(result.contains("THREE"));
    }

    @Test
    void testHandleExceptionAsync() {
        CompletableFuture<String> future = explorer.handleExceptionAsync();
        String result = future.join();
        assertTrue(result.startsWith("Recovered from"));
    }
}
