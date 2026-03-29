package io.huangsam.trial.libs.guava;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestFlattenerExplorer {

    @Test
    void testFlattenAndTrim() {
        FlattenerExplorer explorer = new FlattenerExplorer();
        List<String> inputs = ImmutableList.of("a, b", "c,d", " e ");
        List<String> result = explorer.flattenAndTrim(inputs);

        assertEquals(5, result.size());
        assertEquals("a", result.get(0));
        assertEquals("b", result.get(1));
        assertEquals("c", result.get(2));
        assertEquals("d", result.get(3));
        assertEquals("e", result.get(4));
    }

    @Test
    void testProcessOptional() {
        FlattenerExplorer explorer = new FlattenerExplorer();
        
        assertEquals("HELLO", explorer.processOptional(Optional.of("hello")));
        assertEquals("DEFAULT", explorer.processOptional(Optional.absent()));
    }

    @Test
    void testFilterEvenToString() {
        FlattenerExplorer explorer = new FlattenerExplorer();
        List<Integer> numbers = ImmutableList.of(1, 2, 3, 4, 5);
        List<String> evens = explorer.filterEvenToString(numbers);

        assertEquals(2, evens.size());
        assertTrue(evens.contains("Even:2"));
        assertTrue(evens.contains("Even:4"));
    }
}
