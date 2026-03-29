package io.huangsam.trial.libs.guava;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;

import java.util.List;

/**
 * Demonstrates monadic data flattening and transformation using Guava.
 */
public class DataFlattener {

    /**
     * Flattens a list of strings by splitting each string by comma.
     * This is an example of transformAndConcat, which is like flatMap.
     *
     * @param inputs the list of comma-separated strings
     * @return a flattened list of individual words
     */
    public List<String> flattenAndTrim(List<String> inputs) {
        return FluentIterable.from(inputs)
                .transformAndConcat(new Function<String, Iterable<String>>() {
                    @Override
                    public Iterable<String> apply(String input) {
                        return ImmutableList.copyOf(input.split(","));
                    }
                })
                .transform(new Function<String, String>() {
                    @Override
                    public String apply(String input) {
                        return input.trim();
                    }
                })
                .toList();
    }

    /**
     * Demonstrates monadic chaining with Guava's Optional.
     *
     * @param input an optional string
     * @return the transformed string, or a default value
     */
    public String processOptional(Optional<String> input) {
        return input.transform(new Function<String, String>() {
            @Override
            public String apply(String s) {
                return s.toUpperCase();
            }
        }).or("DEFAULT");
    }

    /**
     * Filters and simplifies a list of integers.
     *
     * @param numbers the input numbers
     * @return a list of strings representing even numbers
     */
    public List<String> filterEvenToString(List<Integer> numbers) {
        return FluentIterable.from(numbers)
                .filter(n -> n % 2 == 0)
                .transform(n -> "Even:" + n)
                .toList();
    }
}
