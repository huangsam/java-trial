package io.huangsam.trial.guava;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.math.LongMath;
import com.google.common.math.Stats;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Google Guava is a useful set of utilities and data structures.
 */
public class TestUtilities {
    @Test
    void testStringWithJoiner() {
        List<String> names = Lists.newArrayList("Alice", "Bob", "Charlie");
        String joinedNames = Joiner.on(", ").join(names);
        assertEquals("Alice, Bob, Charlie", joinedNames);
    }

    @Test
    void testStringWithSplitter() {
        String sentence = "This is a sample sentence.";
        List<String> words = Splitter.on(' ').splitToList(sentence);
        assertTrue(words.stream().noneMatch(word -> word.contains(" ")));
    }

    @Test
    void testLongMathPowerOfTwo() {
        assertEquals(120L, LongMath.factorial(5));
        assertTrue(LongMath.isPowerOfTwo(16L));
    }

    @Test
    void testStatsInstanceMethods() {
        Stats oneToFour = Stats.of(1L, 2L, 3L, 4L);
        assertEquals(4.0, oneToFour.max());
        assertEquals(1.0, oneToFour.min());
        assertEquals(2.5, oneToFour.mean());
        assertEquals(10.0, oneToFour.sum());

        assertEquals(3.0, Stats.meanOf(1, 3, 5));
    }
}
