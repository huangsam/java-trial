package io.huangsam.trial.universal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * {@code Merger<T>} shows the power of a method which exists under a class
 * with generic typing. Note that generics can have more than one type, but
 * we want to keep things simple with a type that is comparable.
 *
 * @see <a href="https://www.youtube.com/watch?v=K1iu1kXkVoA">YouTube on Generics</a>
 */
public class TestMerger {
    private static final Merger<Integer> MERGER = new Merger<>();

    private static final Collection<Integer> C1 = Arrays.asList(3, 7, 2, 9, 8);
    private static final Collection<Integer> C2 = Arrays.asList(1, 10, 4);

    @ParameterizedTest
    @ValueSource(classes = {ArrayList.class, LinkedList.class})
    void testMergeAsListCollection(Class<?> klass) {
        Collection<Integer> mergedSame = MERGER.mergeAs(C1, C1, klass);
        Collection<Integer> mergedDifferent = MERGER.mergeAs(C1, C2, klass);
        assertEquals(C1.size() + C1.size(), mergedSame.size());
        assertEquals(C1.size() + C2.size(), mergedDifferent.size());
    }

    @ParameterizedTest
    @ValueSource(classes = {HashSet.class, LinkedHashSet.class, TreeSet.class})
    void testMergeAsSetCollection(Class<?> klass) {
        Collection<Integer> mergedSame = MERGER.mergeAs(C1, C1, klass);
        Collection<Integer> mergedDifferent = MERGER.mergeAs(C1, C2, klass);
        assertEquals(C1.size(), mergedSame.size());
        assertEquals(C1.size() + C2.size(), mergedDifferent.size());
    }

    @Test
    void testMergeAsIllegalType() {
        assertThrows(IllegalArgumentException.class, () -> MERGER.mergeAs(C1, C1, Number.class));
    }
}
