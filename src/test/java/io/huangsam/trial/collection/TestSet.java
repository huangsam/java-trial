package io.huangsam.trial.collection;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * A {@code Set} is a data structure for collecting a set of items. It can
 * be used for different applications such as depth-first-search, gathering
 * unique values from a stream of data, as well as existence checks.
 */
public class TestSet {
    private final Set<Integer> evenSet = Set.of(2, 4, 6, 8);
    private final Set<Integer> oddSet = Set.of(1, 3, 5, 7);

    @Test
    void testContainsForExistence() {
        assertTrue(evenSet.contains(2));
        assertFalse(oddSet.contains(2));

        assertFalse(evenSet.contains(1));
        assertTrue(oddSet.contains(1));

        assertTrue(evenSet.containsAll(Set.of(4, 6)));
        assertFalse(oddSet.containsAll(Set.of(4, 6)));
    }

    @Test
    void testRetainsAllValues() {
        Set<Integer> set = new HashSet<>(evenSet);

        set.retainAll(evenSet);

        assertEquals(evenSet.size(), set.size());
    }

    @Test
    void testRetainsNoValues() {
        Set<Integer> set = new HashSet<>(evenSet);

        set.retainAll(oddSet);

        assertEquals(0, set.size());
    }
}
