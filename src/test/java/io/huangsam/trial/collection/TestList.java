package io.huangsam.trial.collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestList {
    private final List<Integer> list = new ArrayList<>();

    @BeforeEach
    void resetList() {
        list.clear();
        list.addAll(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
    }

    @Test
    void testOriginalSize() {
        assertEquals(9, list.size());
    }

    @Test
    void testGetStartAndEnd() {
        assertEquals(1, list.get(0));
        assertEquals(9, list.get(8));
    }

    @Test
    void testGetOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
    }

    @Test
    void testSubListAndSetValue() {
        List<Integer> subList = list.subList(1, 3);

        assertEquals(2, subList.get(0));

        subList.set(1, -1);

        assertEquals(-1, subList.get(1));
        assertEquals(-1, list.get(2));
    }
}
