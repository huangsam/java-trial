package org.huangsam.sample.universal;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestListMerger {
    @Test
    void testMerge() {
        ListMerger<Integer> merger = new ListMerger<>();

        ArrayList<Integer> list1 = new ArrayList<>(Arrays.asList(3, 7, 2, 9, 8));
        ArrayList<Integer> list2 = new ArrayList<>(Arrays.asList(1, 10, 4));

        List<Integer> mergedList = merger.merge(list1, list2);
        assertEquals(list1.size() + list2.size(), mergedList.size());

        Integer previous = mergedList.get(0);
        for (Integer current : mergedList.subList(1, mergedList.size())) {
            assertTrue(previous < current);
            previous = current;
        }
    }
}
