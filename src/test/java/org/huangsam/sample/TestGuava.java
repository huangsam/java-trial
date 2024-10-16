package org.huangsam.sample;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Google Guava is a useful set of utilities and data structures.
 *
 * @see <a href="https://www.baeldung.com/guava-multiset">Baeldung on Multiset</a>
 */
public class TestGuava {
    @Test
    void testMultiSet() {
        Multiset<String> bookStore = HashMultiset.create();

        String key = "Potter";

        bookStore.add(key);
        bookStore.add(key);
        bookStore.add(key);
        assertTrue(bookStore.contains(key));
        assertEquals(3, bookStore.count(key));

        bookStore.remove(key);
        assertEquals(2, bookStore.count(key));

        int expectedCount = 50;
        bookStore.setCount(key, expectedCount);
        assertEquals(expectedCount, bookStore.count(key));

        int expectedNewCount = 100;
        assertTrue(bookStore.setCount(key, expectedCount, expectedNewCount));
        assertFalse(bookStore.setCount(key, expectedCount, expectedNewCount));

        assertThrows(IllegalArgumentException.class, () -> bookStore.setCount(key, -1));
    }
}
