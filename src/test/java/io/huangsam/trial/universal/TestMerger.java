package io.huangsam.trial.universal;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

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

    @Test
    void testMergeAsArrayList() {
        Collection<Integer> mergedSame = MERGER.mergeAs(C1, C1, ArrayList.class);
        Collection<Integer> mergedDifferent = MERGER.mergeAs(C1, C2, ArrayList.class);
        assertEquals(C1.size() + C1.size(), mergedSame.size());
        assertEquals(C1.size() + C2.size(), mergedDifferent.size());
    }

    @Test
    void testMergeAsHashSet() {
        Collection<Integer> mergedSame = MERGER.mergeAs(C1, C1, HashSet.class);
        Collection<Integer> mergedDifferent = MERGER.mergeAs(C1, C2, HashSet.class);
        assertEquals(C1.size(), mergedSame.size());
        assertEquals(C1.size() + C2.size(), mergedDifferent.size());
    }

    @Test
    void testMergeAsIllegalType() {
        assertThrows(IllegalArgumentException.class, () -> MERGER.mergeAs(C1, C1, Number.class));
    }
}
