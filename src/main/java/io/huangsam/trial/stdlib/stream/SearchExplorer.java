package io.huangsam.trial.stdlib.stream;

import java.util.List;
import java.util.Optional;

/**
 * Demonstrates the use of the Stream API for finding and searching operations.
 */
public class SearchExplorer {

    /**
     * Finds the first string that starts with a specific prefix.
     *
     * @param strings the strings to search
     * @param prefix  the prefix to match
     * @return an Optional containing the first matching string, or empty if none found
     */
    public Optional<String> findFirstMatching(List<String> strings, String prefix) {
        return strings.stream()
                .filter(s -> s.startsWith(prefix))
                .findFirst();
    }
}
