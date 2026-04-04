package io.huangsam.trial.stdlib.functional;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Explores custom and standard functional interfaces in Java.
 * Demonstrates the power of lambda expressions and method references.
 */
public class FunctionalExplorer {
    /**
     * Constructs a functional explorer.
     */
    public FunctionalExplorer() {
        // Default constructor
    }

    /**
     * A custom functional interface for string transformations.
     */
    @FunctionalInterface
    public interface StringTransformer {
        /**
         * Transforms a string.
         *
         * @param input the input string
         * @return the transformed string
         */
        String transform(String input);
    }

    /**
     * Transforms a string using a provided transformer.
     *
     * @param input       the input string
     * @param transformer the transformer logic
     * @return the transformed string
     */
    public String useTransformer(String input, StringTransformer transformer) {
        return transformer.transform(input);
    }

    /**
     * Filters a value based on a predicate.
     *
     * @param <T>       the type of the value
     * @param value     the value to test
     * @param predicate the condition
     * @return true if the condition is met
     */
    public <T> boolean checkCondition(T value, Predicate<T> predicate) {
        return predicate.test(value);
    }

    /**
     * Applies a function to a value.
     *
     * @param <T>      the type of the input value
     * @param <R>      the type of the mapped result
     * @param value    the input value
     * @param function the mapping logic
     * @return the mapped result
     */
    public <T, R> R mapValue(T value, Function<T, R> function) {
        return function.apply(value);
    }

    /**
     * Combines two values using a BiFunction.
     *
     * @param <T>      the type of the first value
     * @param <U>      the type of the second value
     * @param <R>      the type of the combined result
     * @param t1       the first value
     * @param t2       the second value
     * @param combiner the combination logic
     * @return the combined result
     */
    public <T, U, R> R combine(T t1, U t2, BiFunction<T, U, R> combiner) {
        return combiner.apply(t1, t2);
    }
}
