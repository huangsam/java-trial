package io.huangsam.trial.stdlib.util;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.TreeSet;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestMerge {
    private static final MergeExplorer<Integer> MERGER = new MergeExplorer<>();

    private static final Collection<Integer> C1 = Arrays.asList(3, 7, 2, 9, 8);
    private static final Collection<Integer> C2 = Arrays.asList(1, 10, 4);

    static Stream<Supplier<Collection<Integer>>> listSuppliers() {
        return Stream.of(ArrayList::new, LinkedList::new);
    }

    @ParameterizedTest
    @MethodSource("listSuppliers")
    void testMergeAsListCollection(Supplier<Collection<Integer>> factory) {
        Collection<Integer> mergedSame = MERGER.mergeAs(C1, C1, factory);
        Collection<Integer> mergedDifferent = MERGER.mergeAs(C1, C2, factory);
        assertEquals(C1.size() + C1.size(), mergedSame.size());
        assertEquals(C1.size() + C2.size(), mergedDifferent.size());
    }

    static Stream<Supplier<Collection<Integer>>> setSuppliers() {
        return Stream.of(HashSet::new, LinkedHashSet::new, TreeSet::new);
    }

    @ParameterizedTest
    @MethodSource("setSuppliers")
    void testMergeAsSetCollection(Supplier<Collection<Integer>> factory) {
        Collection<Integer> mergedSame = MERGER.mergeAs(C1, C1, factory);
        Collection<Integer> mergedDifferent = MERGER.mergeAs(C1, C2, factory);
        assertEquals(C1.size(), mergedSame.size());
        assertEquals(C1.size() + C2.size(), mergedDifferent.size());
    }
}
