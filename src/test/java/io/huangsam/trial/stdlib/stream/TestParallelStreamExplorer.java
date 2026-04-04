package io.huangsam.trial.stdlib.stream;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for {@link ParallelStreamExplorer}.
 */
public class TestParallelStreamExplorer {

    private final ParallelStreamExplorer explorer = new ParallelStreamExplorer();

    @Test
    void testSumSequential() {
        assertEquals(55L, explorer.sumSequential(10));
    }

    @Test
    void testSumParallel() {
        assertEquals(55L, explorer.sumParallel(10));
    }

    @Test
    void testProcessInParallel() {
        List<String> inputs = List.of("a", "b", "c");
        List<String> result = explorer.processInParallel(inputs);
        assertEquals(List.of("A", "B", "C"), result);
    }
}
