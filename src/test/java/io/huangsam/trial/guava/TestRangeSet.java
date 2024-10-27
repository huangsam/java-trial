package io.huangsam.trial.guava;

import com.google.common.collect.Range;
import com.google.common.collect.RangeSet;
import com.google.common.collect.TreeRangeSet;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Google Guava is a useful set of utilities and data structures.
 *
 * @see <a href="https://www.baeldung.com/guava-rangeset">Baeldung on RangeSet</a>
 */
public class TestRangeSet {
    public static final Logger LOG = LoggerFactory.getLogger(TestRangeSet.class);

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
