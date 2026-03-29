package io.huangsam.trial.stdlib.stream;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestSearch {

    private final SearchExplorer explorer = new SearchExplorer();

    @Test
    void testFindFirstMatchingSuccess() {
        List<String> input = Arrays.asList("apple", "banana", "cherry");
        Optional<String> result = explorer.findFirstMatching(input, "b");
        assertTrue(result.isPresent());
        assertEquals("banana", result.get());
    }

    @Test
    void testFindFirstMatchingFailure() {
        List<String> input = Arrays.asList("apple", "banana", "cherry");
        Optional<String> result = explorer.findFirstMatching(input, "x");
        assertTrue(result.isEmpty());
    }
}
