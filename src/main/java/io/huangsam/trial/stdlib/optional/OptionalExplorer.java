package io.huangsam.trial.stdlib.optional;

import java.util.Optional;

/**
 * Explores patterns for using {@link Optional} effectively.
 * Demonstrates modern Java methods like ifPresentOrElse, or, and flatMap.
 */
public class OptionalExplorer {

    /**
     * Executes an action if the optional has a value, otherwise executes another action.
     *
     * @param input optional string
     * @return true if the value was present
     */
    public boolean processIfPresentOrElse(Optional<String> input) {
        final boolean[] result = new boolean[1];
        input.ifPresentOrElse(
                v -> result[0] = true,
                () -> result[0] = false
        );
        return result[0];
    }

    /**
     * Provides a default value if the optional is empty using {@code or}.
     *
     * @param input optional string
     * @return another optional with a default if input was empty
     */
    public Optional<String> withDefault(Optional<String> input) {
        return input.or(() -> Optional.of("DEFAULT"));
    }

    /**
     * Transforms the value if present using {@code flatMap}.
     *
     * @param input optional string
     * @return transformed optional
     */
    public Optional<Integer> transformToInt(Optional<String> input) {
        return input.flatMap(v -> {
            try {
                return Optional.of(Integer.parseInt(v));
            } catch (NumberFormatException e) {
                return Optional.empty();
            }
        });
    }

    /**
     * Retrieves the value or throws an exception if empty.
     *
     * @param input optional string
     * @return the unwrapped value
     * @throws IllegalStateException if the value is missing
     */
    public String getOrThrow(Optional<String> input) {
        return input.orElseThrow(() -> new IllegalStateException("Value is required"));
    }
}
