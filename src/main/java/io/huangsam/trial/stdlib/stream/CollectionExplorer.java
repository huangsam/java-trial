package io.huangsam.trial.stdlib.stream;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Demonstrates the use of the Stream API for advanced terminal operations with collectors.
 */
public class CollectionExplorer {

    /**
     * Groups a list of strings by their length.
     *
     * @param strings the strings to process
     * @return a map where keys are lengths and values are lists of strings with that length
     */
    public Map<Integer, List<String>> groupByLength(List<String> strings) {
        return strings.stream()
                .collect(Collectors.groupingBy(String::length));
    }

    /**
     * Partitions numbers into two groups: even and odd.
     *
     * @param numbers the numbers to partition
     * @return a map where true = even and false = odd
     */
    public Map<Boolean, List<Integer>> partitionByEven(List<Integer> numbers) {
        return numbers.stream()
                .collect(Collectors.partitioningBy(n -> n % 2 == 0));
    }
}
