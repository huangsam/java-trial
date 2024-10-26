package io.huangsam.trial.collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestMap {
    private Map<Integer, Integer> mapping;

    @BeforeEach
    void setUpMapping() {
        if (mapping == null) {
            mapping = new HashMap<>();
        } else {
            mapping.clear();
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {5, 10, 15})
    void testMapFillAndVerifyEntries(int expectedSize) {
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
}
