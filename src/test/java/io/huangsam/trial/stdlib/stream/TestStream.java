package io.huangsam.trial.stdlib.stream;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestStream {

    private final StreamExplorer explorer = new StreamExplorer();

    @Test
    void testFlattenLists() {
        List<List<Integer>> nestedLists = Arrays.asList(
                Arrays.asList(1, 2),
                Arrays.asList(3, 4),
                Arrays.asList(5, 6));
        List<Integer> result = explorer.flattenLists(nestedLists);
        assertEquals(Arrays.asList(1, 2, 3, 4, 5, 6), result);
    }

    @Test
    void testFlattenToChars() {
        List<String> strings = Arrays.asList("A", "BC");
        List<Integer> result = explorer.flattenToChars(strings).boxed().toList();
        // A=65, B=66, C=67
        assertEquals(Arrays.asList(65, 66, 67), result);
    }

    @Test
    void testFlattenOptionals() {
        List<Optional<String>> optionals = Arrays.asList(
                Optional.of("Java"),
                Optional.empty(),
                Optional.of("Stream"));
        List<String> result = explorer.flattenOptionals(optionals);
        assertEquals(Arrays.asList("Java", "Stream"), result);
    }

    @Test
    void testExpandWithNeighbors() {
        List<Integer> numbers = Arrays.asList(5, 10);
        List<Integer> result = explorer.expandWithNeighbors(numbers);
        assertEquals(Arrays.asList(4, 5, 6, 9, 10, 11), result);
    }
}
