package org.huangsam.sample.universal;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Merger<T> {
    public List<T> mergeList(List<T> list1, List<T> list2) {
        List<T> mergedList = new ArrayList<>(list1.size() + list2.size());
        mergedList.addAll(list1);
        mergedList.addAll(list2);
        return mergedList;
    }

    public Set<T> mergeSet(Set<T> set1, Set<T> set2) {
        Set<T> mergedSet = new HashSet<>(set1.size() + set2.size());
        mergedSet.addAll(set1);
        mergedSet.addAll(set2);
        return mergedSet;
    }
}
