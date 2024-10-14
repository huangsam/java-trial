package org.huangsam.sample.universal;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestMerger {
    private static final Merger<Integer> MERGER = new Merger<>();

    private static final List<Integer> L1 = Arrays.asList(3, 7, 2, 9, 8);
    private static final List<Integer> L2 = Arrays.asList(1, 10, 4);

    private static final Set<Integer> S1 = new HashSet<>(L1);
    private static final Set<Integer> S2 = new HashSet<>(L2);

    @Test
    void testMergeList() {
        List<Integer> merged = MERGER.mergeList(L1, L2);
        assertEquals(L1.size() + L2.size(), merged.size());
    }

    @Test
    void testMergeSet() {
        Set<Integer> merged = MERGER.mergeSet(S1, S2);
        assertEquals(S1.size() + S2.size(), merged.size());
    }
}
