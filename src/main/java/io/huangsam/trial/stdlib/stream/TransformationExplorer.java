package io.huangsam.trial.stdlib.stream;

import java.util.List;

/**
 * Demonstrates the use of the Stream API for transformation and filtering.
 */
public class TransformationExplorer {

    /**
     * Filters strings by length and transforms them to uppercase.
     *
     * @param strings   the strings to process
     * @param minLength the minimum length required to keep a string
     * @return a list of uppercase strings that meet the length criteria
     */
    public List<String> filterAndMap(List<String> strings, int minLength) {
        return strings.stream()
                .filter(s -> s.length() >= minLength)
                .map(String::toUpperCase)
                .toList();
    }

    /**
     * Gets distinct elements from a list and sorts them according to their natural order.
     *
     * @param <T>   the type of elements (must be Comparable)
     * @param items the items to process
     * @return a sorted list of distinct elements
     */
    public <T extends Comparable<? super T>> List<T> distinctAndSorted(List<T> items) {
        return items.stream()
                .distinct()
                .sorted()
                .toList();
    }
}
