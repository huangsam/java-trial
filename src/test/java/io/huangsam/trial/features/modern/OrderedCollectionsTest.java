package io.huangsam.trial.features.modern;

import org.junit.jupiter.api.Test;

import java.util.SequencedCollection;
import java.util.SequencedMap;
import java.util.SequencedSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderedCollectionsTest {

    @Test
    void testSequencedList() {
        OrderedCollections collections = new OrderedCollections();
        SequencedCollection<String> list = collections.getOrderedList();
        
        assertEquals("Start", list.getFirst());
        assertEquals("End", list.getLast());
        
        SequencedCollection<String> reversed = collections.reverse(list);
        assertEquals("End", reversed.getFirst());
        assertEquals("Start", reversed.getLast());
    }

    @Test
    void testSequencedSet() {
        OrderedCollections collections = new OrderedCollections();
        SequencedSet<String> set = collections.getOrderedSet();

        assertEquals("RealFirst", set.getFirst());
    }

    @Test
    void testSequencedMap() {
        OrderedCollections collections = new OrderedCollections();
        SequencedMap<Integer, String> map = collections.getOrderedMap();

        assertEquals(0, map.firstEntry().getKey());
        assertEquals("Zero", map.firstEntry().getValue());
    }
}
