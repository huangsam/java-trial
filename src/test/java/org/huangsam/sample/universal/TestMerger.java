package org.huangsam.sample.universal;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestMerger {
    private static final Merger<Integer> MERGER = new Merger<>();

    private static final Collection<Integer> C1 = Arrays.asList(3, 7, 2, 9, 8);
    private static final Collection<Integer> C2 = Arrays.asList(1, 10, 4);

    @Test
    void testMergeList() {
        Collection<Integer> mergedSame = MERGER.mergeAs(C1, C1, ArrayList.class);
        Collection<Integer> mergedDifferent = MERGER.mergeAs(C1, C2, ArrayList.class);
        assertEquals(C1.size() + C1.size(), mergedSame.size());
        assertEquals(C1.size() + C2.size(), mergedDifferent.size());
    }

    @Test
    void testMergeSet() {
        Collection<Integer> mergedSame = MERGER.mergeAs(C1, C1, HashSet.class);
        Collection<Integer> mergedDifferent = MERGER.mergeAs(C1, C2, HashSet.class);
        assertEquals(C1.size(), mergedSame.size());
        assertEquals(C1.size() + C2.size(), mergedDifferent.size());
    }
}
