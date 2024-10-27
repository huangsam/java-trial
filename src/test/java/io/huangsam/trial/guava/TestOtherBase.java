package io.huangsam.trial.guava;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Google Guava is a useful set of utilities and data structures.
 */
public class TestOtherBase {
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
}
