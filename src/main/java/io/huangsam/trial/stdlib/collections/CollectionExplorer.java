package io.huangsam.trial.stdlib.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Demonstrates the use of standard Java collections.
 */
public class CollectionExplorer {
    /**
     * Constructs a collection explorer.
     */
    public CollectionExplorer() {
        // Default constructor
    }

    /**
     * Creates a new ArrayList.
     *
     * @param <T> the type
     * @return the list
     */
    public <T> List<T> newArrayList() {
        return new ArrayList<>();
    }

    /**
     * Creates a new HashSet.
     *
     * @param <T> the type
     * @return the set
     */
    public <T> Set<T> newHashSet() {
        return new HashSet<>();
    }

    /**
     * Creates a new TreeMap.
     *
     * @param <K> the key type
     * @param <V> the value type
     * @return the map
     */
    public <K, V> Map<K, V> newTreeMap() {
        return new TreeMap<>();
    }

    /**
     * Creates a new LinkedList as a Deque.
     *
     * @param <T> the type
     * @return the deque
     */
    public <T> Deque<T> newLinkedListDeque() {
        return new LinkedList<>();
    }

    /**
     * Adds all elements to a collection.
     *
     * @param <T>        the type
     * @param collection the target collection
     * @param elements   the elements to add
     */
    @SafeVarargs
    public final <T> void addAll(java.util.Collection<T> collection, T... elements) {
        collection.addAll(Arrays.asList(elements));
    }
}
