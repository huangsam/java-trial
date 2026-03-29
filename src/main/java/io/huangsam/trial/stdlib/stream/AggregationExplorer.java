package io.huangsam.trial.stdlib.stream;

import java.util.List;

/**
 * Demonstrates the use of the Stream API for aggregation operations like reduce.
 */
public class AggregationExplorer {

    /**
     * Sums all numbers in a list using the reduce operation.
     *
     * @param numbers the numbers to sum
     * @return the total sum
     */
    public int sumWithReduce(List<Integer> numbers) {
        return numbers.stream()
                .reduce(0, Integer::sum);
    }
}
