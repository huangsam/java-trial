package io.huangsam.trial.stdlib.stream;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Demonstrates the use of the Stream API, specifically focusing on flattening operations.
 */
public class StreamExplorer {

    /**
     * Flattens a list of lists into a single list.
     *
     * @param <T>         the type of elements
     * @param nestedLists the list of lists to flatten
     * @return a flattened list
     */
    public <T> List<T> flattenLists(List<List<T>> nestedLists) {
        return nestedLists.stream()
                .flatMap(Collection::stream)
                .toList();
    }

    /**
     * Flattens a list of strings by converting each string to its individual characters as ints.
     *
     * @param strings the strings to flatten
     * @return an IntStream of character points
     */
    public IntStream flattenToChars(List<String> strings) {
        return strings.stream()
                .flatMapToInt(String::chars);
    }

    /**
     * Flattens a list of Optionals into a list of the present values.
     *
     * @param <T>       the type of elements
     * @param optionals the list of Optionals
     * @return a list containing only the present values
     */
    public <T> List<T> flattenOptionals(List<Optional<T>> optionals) {
        return optionals.stream()
                .flatMap(Optional::stream)
                .toList();
    }

    /**
     * Multiplies each number in a list by its neighbors (e.g., [1, 2, 3] -> [0, 1, 2, 1, 2, 3, 2, 3, 4]).
     *
     * @param numbers the numbers to expand
     * @return a flattened stream of neighboring numbers
     */
    public List<Integer> expandWithNeighbors(List<Integer> numbers) {
        return numbers.stream()
                .flatMap(n -> Stream.of(n - 1, n, n + 1))
                .toList();
    }
}
