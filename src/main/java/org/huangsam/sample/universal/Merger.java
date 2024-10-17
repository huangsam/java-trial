package org.huangsam.sample.universal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Merger<T extends Comparable<T>> {
    public Collection<T> mergeAs(Collection<T> c1, Collection<T> c2, Class<?> as) {
        Collection<T> merged;

        // https://stackoverflow.com/a/3949379
        if (List.class.isAssignableFrom(as)) {
            merged = new ArrayList<>(c1.size() + c2.size());
        } else if (Set.class.isAssignableFrom(as)) {
            merged = new HashSet<>(c1.size() + c2.size());
        } else {
            throw new IllegalArgumentException("Cannot merge collections as " + as.getName());
        }

        merged.addAll(c1);
        merged.addAll(c2);
        return merged;
    }
}
