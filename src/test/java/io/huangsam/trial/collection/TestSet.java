package io.huangsam.trial.collection;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestSet {
    private final Set<Integer> evenSet = Set.of(2, 4, 6, 8);
    private final Set<Integer> oddSet = Set.of(1, 3, 5, 7);

    @Test
    void testContainsForExistence() {
        assertTrue(evenSet.contains(2));
        assertFalse(oddSet.contains(2));

        assertFalse(evenSet.contains(1));
        assertTrue(oddSet.contains(1));

        assertTrue(evenSet.containsAll(Arrays.asList(4, 6)));
        assertFalse(oddSet.containsAll(Arrays.asList(4, 6)));
    }
}
