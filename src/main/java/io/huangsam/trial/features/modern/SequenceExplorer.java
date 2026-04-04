package io.huangsam.trial.features.modern;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.SequencedCollection;
import java.util.SequencedMap;
import java.util.SequencedSet;

/**
 * Demonstrates Java 21+ Sequenced Collections.
 */
public class SequenceExplorer {
    /**
     * Constructs a sequence explorer.
     */
    public SequenceExplorer() {
        // Default constructor
    }

    /**
     * Demonstrates SequencedCollection (List).
     *
     * @return a sequenced collection of strings
     */
    public SequencedCollection<String> getOrderedList() {
        SequencedCollection<String> list = new ArrayList<>();
        list.add("Middle");
        list.addFirst("Start");
        list.addLast("End");
        return list;
    }

    /**
     * Demonstrates SequencedSet.
     *
     * @return a sequenced set of strings
     */
    public SequencedSet<String> getOrderedSet() {
        SequencedSet<String> set = new LinkedHashSet<>();
        set.add("First");
        set.add("Second");
        set.addFirst("RealFirst");
        return set;
    }

    /**
     * Demonstrates SequencedMap.
     *
     * @return a sequenced map of IDs to values
     */
    public SequencedMap<Integer, String> getOrderedMap() {
        SequencedMap<Integer, String> map = new LinkedHashMap<>();
        map.put(1, "One");
        map.put(2, "Two");
        map.putFirst(0, "Zero");
        return map;
    }

    /**
     * Reverses a sequenced collection.
     *
     * @param collection the collection to reverse
     * @return the reversed collection view
     */
    public SequencedCollection<String> reverse(SequencedCollection<String> collection) {
        return collection.reversed();
    }
}
