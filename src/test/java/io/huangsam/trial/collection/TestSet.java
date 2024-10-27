package io.huangsam.trial.collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestSet {
    private final Set<Integer> evenSet = new HashSet<>();
    private final Set<Integer> oddSet = new HashSet<>();

    @BeforeEach
    void resetSet() {
        evenSet.clear();
        evenSet.addAll(Arrays.asList(2, 4, 6, 8, 10));

        oddSet.clear();
        oddSet.addAll(Arrays.asList(1, 3, 5, 7, 9));
    }

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
