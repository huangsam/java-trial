package io.huangsam.trial.stdlib.generics;

import java.util.Collection;
import java.util.function.Supplier;

/**
 * Demonstrates merging different collection types.
 *
 * @param <T> the type of elements
 */
public class MergeExplorer<T extends Comparable<T>> {
    /**
     * Constructs a merge explorer.
     */
    public MergeExplorer() {
        // Default constructor
    }
    /**
     * Merges two collections into a new collection created by the specified
     * factory.
     *
     * @param <C>     the type of the collection
     * @param c1      the first collection
     * @param c2      the second collection
     * @param factory the factory supplying the new collection
     * @return the merged collection
     */
    public <C extends Collection<T>> C mergeAs(Collection<T> c1, Collection<T> c2, Supplier<C> factory) {
        C merged = factory.get();
        merged.addAll(c1);
        merged.addAll(c2);
        return merged;
    }
}
