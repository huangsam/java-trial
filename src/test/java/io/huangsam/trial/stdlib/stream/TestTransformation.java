package io.huangsam.trial.stdlib.stream;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestTransformation {

    private final TransformationExplorer explorer = new TransformationExplorer();

    @Test
    void testFilterAndMap() {
        List<String> input = Arrays.asList("a", "bb", "ccc", "dddd");
        List<String> result = explorer.filterAndMap(input, 3);
        assertEquals(Arrays.asList("CCC", "DDDD"), result);
    }

    @Test
    void testDistinctAndSorted() {
        List<Integer> input = Arrays.asList(3, 1, 2, 3, 2, 1);
        List<Integer> result = explorer.distinctAndSorted(input);
        assertEquals(Arrays.asList(1, 2, 3), result);
    }
}
