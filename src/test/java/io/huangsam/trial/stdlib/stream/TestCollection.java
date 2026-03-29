package io.huangsam.trial.stdlib.stream;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestCollection {

    private final CollectionExplorer explorer = new CollectionExplorer();

    @Test
    void testGroupByLength() {
        List<String> input = Arrays.asList("a", "b", "aa", "bb", "ccc");
        Map<Integer, List<String>> result = explorer.groupByLength(input);

        assertEquals(3, result.size());
        assertEquals(Arrays.asList("a", "b"), result.get(1));
        assertEquals(Arrays.asList("aa", "bb"), result.get(2));
        assertEquals(Arrays.asList("ccc"), result.get(3));
    }

    @Test
    void testPartitionByEven() {
        List<Integer> input = Arrays.asList(1, 2, 3, 4, 5, 6);
        Map<Boolean, List<Integer>> result = explorer.partitionByEven(input);

        assertTrue(result.containsKey(true));
        assertTrue(result.containsKey(false));
        assertEquals(Arrays.asList(2, 4, 6), result.get(true));
        assertEquals(Arrays.asList(1, 3, 5), result.get(false));
    }
}
