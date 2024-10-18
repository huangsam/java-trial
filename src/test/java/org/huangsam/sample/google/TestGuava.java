package org.huangsam.sample.google;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import com.google.common.collect.Range;
import com.google.common.collect.RangeSet;
import com.google.common.collect.TreeRangeSet;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Google Guava is a useful set of utilities and data structures.
 *
 * @see <a href="https://www.baeldung.com/guava-multiset">Baeldung on Multiset</a>
 * @see <a href="https://www.baeldung.com/guava-rangeset">Baeldung on RangeSet</a>
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

    @Test
    void testRangeSet() {
        RangeSet<Integer> numberRangeSet = TreeRangeSet.create();

        numberRangeSet.add(Range.closed(0, 2));
        numberRangeSet.add(Range.closed(3, 5));
        numberRangeSet.add(Range.closed(6, 8));

        assertTrue(numberRangeSet.contains(1));
        assertFalse(numberRangeSet.contains(9));

        numberRangeSet.clear();
        numberRangeSet.add(Range.closed(0, 5));
        numberRangeSet.remove(Range.closed(1, 4));
        assertTrue(numberRangeSet.contains(0));
        assertFalse(numberRangeSet.contains(1));
        assertFalse(numberRangeSet.contains(4));
        assertTrue(numberRangeSet.contains(5));

        numberRangeSet.clear();
        numberRangeSet.add(Range.closed(0, 2));
        numberRangeSet.add(Range.closed(3, 5));
        numberRangeSet.add(Range.closed(6, 8));

        Range<Integer> span = numberRangeSet.span();
        assertEquals(0, span.lowerEndpoint().intValue());
        assertEquals(8, span.upperEndpoint().intValue());
    }
}
