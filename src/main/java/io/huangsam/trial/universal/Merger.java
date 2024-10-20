package io.huangsam.trial.universal;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.TreeSet;

public class Merger<T extends Comparable<T>> {
    public Collection<T> mergeAs(Collection<T> c1, Collection<T> c2, Class<?> as) {
        Collection<T> merged;

        String className = as.getName();
        merged = switch (className) {
            case "java.util.ArrayDeque" -> new ArrayDeque<>();
            case "java.util.ArrayList" -> new ArrayList<>();
            case "java.util.HashSet" -> new HashSet<>();
            case "java.util.LinkedHashSet" -> new LinkedHashSet<>();
            case "java.util.LinkedList" -> new LinkedList<>();
            case "java.util.TreeSet" -> new TreeSet<>();
            default -> throw new IllegalArgumentException("Cannot merge with class " + className);
        };

        merged.addAll(c1);
        merged.addAll(c2);
        return merged;
    }
}
