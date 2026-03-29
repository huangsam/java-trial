package io.huangsam.trial.features.modern;

import org.junit.jupiter.api.Test;

import java.util.SequencedCollection;
import java.util.SequencedMap;
import java.util.SequencedSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSequenceExplorer {

    @Test
    void testSequencedList() {
        SequenceExplorer explorer = new SequenceExplorer();
        SequencedCollection<String> list = explorer.getOrderedList();
        
        assertEquals("Start", list.getFirst());
        assertEquals("End", list.getLast());
        
        SequencedCollection<String> reversed = explorer.reverse(list);
        assertEquals("End", reversed.getFirst());
        assertEquals("Start", reversed.getLast());
    }

    @Test
    void testSequencedSet() {
        SequenceExplorer explorer = new SequenceExplorer();
        SequencedSet<String> set = explorer.getOrderedSet();

        assertEquals("RealFirst", set.getFirst());
    }

    @Test
    void testSequencedMap() {
        SequenceExplorer explorer = new SequenceExplorer();
        SequencedMap<Integer, String> map = explorer.getOrderedMap();

        assertEquals(0, map.firstEntry().getKey());
        assertEquals("Zero", map.firstEntry().getValue());
    }
}
