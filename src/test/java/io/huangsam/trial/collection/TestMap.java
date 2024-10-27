package io.huangsam.trial.collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * A {@code Map} is a data structure for setting keys and values. It can be
 * perceived as a lookup table, saving computed results in well-defined keys so
 * that the same work does not need to be repeated.
 */
public class TestMap {
    private final Map<Integer, Integer> mapping = new HashMap<>();

    @BeforeEach
    void resetMapping() {
        mapping.clear();
    }

    @ParameterizedTest
    @ValueSource(ints = {5, 10, 15})
    void testFillAndVerifyValues(int expectedSize) {
        Stream.iterate(3, num -> num + 6)
                .limit(expectedSize)
                .forEach(num -> mapping.put(num, num * 2));

        assertEquals(expectedSize, mapping.size());

        assertEquals(6, mapping.get(3));
        assertEquals(18, mapping.get(9));

        mapping.forEach((key, val) -> {
            assertEquals(0, key % 3);
            assertEquals(1, key % 2);

            assertEquals(0, val % 3);
            assertEquals(0, val % 2);
        });
    }

    @Test
    void testPutNewAndOldKeys() {
        assertNull(mapping.putIfAbsent(5, 24));
        assertEquals(24, mapping.putIfAbsent(5, 18));
        assertEquals(24, mapping.putIfAbsent(5, 6));
        assertEquals(24, mapping.get(5));
    }

    @Test
    void testComputeNewAndOldKeys() {
        assertEquals(60, mapping.computeIfAbsent(1, k -> k * 60));
        assertEquals(60, mapping.computeIfAbsent(1, k -> k * 30));
        assertEquals(60, mapping.get(1));
    }

    @Test
    void testGetNewAndOldValues() {
        mapping.put(1, 1);
        assertEquals(1, mapping.getOrDefault(1, 8));
        assertEquals(8, mapping.getOrDefault(2, 8));
    }

    @Test
    void testReplaceAllValues() {
        mapping.put(3, 4);
        mapping.put(5, 6);
        mapping.put(7, 8);

        mapping.replaceAll((k, v) -> k * v);

        assertEquals(12, mapping.get(3));
        assertEquals(30, mapping.get(5));
        assertEquals(56, mapping.get(7));
    }
}
