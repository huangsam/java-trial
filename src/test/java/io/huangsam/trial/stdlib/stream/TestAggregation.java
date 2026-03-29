package io.huangsam.trial.stdlib.stream;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestAggregation {

    private final AggregationExplorer explorer = new AggregationExplorer();

    @Test
    void testSumWithReduce() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        int result = explorer.sumWithReduce(numbers);
        assertEquals(15, result);
    }
}
