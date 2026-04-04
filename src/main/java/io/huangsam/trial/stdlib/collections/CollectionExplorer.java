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

public class CollectionExplorer {
    public <T> List<T> newArrayList() {
        return new ArrayList<>();
    }

    public <T> Set<T> newHashSet() {
        return new HashSet<>();
    }

    public <K, V> Map<K, V> newTreeMap() {
        return new TreeMap<>();
    }

    public <T> Deque<T> newLinkedListDeque() {
        return new LinkedList<>();
    }

    @SafeVarargs
    public final <T> void addAll(java.util.Collection<T> collection, T... elements) {
        collection.addAll(Arrays.asList(elements));
    }
}
