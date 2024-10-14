package org.huangsam.sample.universal;

import java.util.ArrayList;
import java.util.List;

public class ListMerger<T extends Comparable<T>> {
    public List<T> merge(List<T> list1, List<T> list2) {
        List<T> mergedList = new ArrayList<>(list1.size() + list2.size());
        mergedList.addAll(list1);
        mergedList.addAll(list2);
        mergedList.sort(T::compareTo);
        return mergedList;
    }
}
