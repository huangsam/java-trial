package io.huangsam.trial.libs.guava;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DataFlattenerTest {

    @Test
    void testFlattenAndTrim() {
        DataFlattener flattener = new DataFlattener();
        List<String> inputs = ImmutableList.of("a, b", "c,d", " e ");
        List<String> result = flattener.flattenAndTrim(inputs);

        assertEquals(5, result.size());
        assertEquals("a", result.get(0));
        assertEquals("b", result.get(1));
        assertEquals("c", result.get(2));
        assertEquals("d", result.get(3));
        assertEquals("e", result.get(4));
    }

    @Test
    void testProcessOptional() {
        DataFlattener flattener = new DataFlattener();
        
        assertEquals("HELLO", flattener.processOptional(Optional.of("hello")));
        assertEquals("DEFAULT", flattener.processOptional(Optional.absent()));
    }

    @Test
    void testFilterEvenToString() {
        DataFlattener flattener = new DataFlattener();
        List<Integer> numbers = ImmutableList.of(1, 2, 3, 4, 5);
        List<String> evens = flattener.filterEvenToString(numbers);

        assertEquals(2, evens.size());
        assertTrue(evens.contains("Even:2"));
        assertTrue(evens.contains("Even:4"));
    }
}
