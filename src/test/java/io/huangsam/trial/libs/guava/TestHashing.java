package io.huangsam.trial.libs.guava;

import com.google.common.hash.HashCode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestHashing {

    private final HashingExplorer explorer = new HashingExplorer();

    @Test
    void testHashingAlgorithms() {
        String input = "Hello, Google Guava Hashing!";
        HashCode h1 = explorer.murmur3(input);
        HashCode h2 = explorer.sha256(input);

        assertNotNull(h1);
        assertNotNull(h2);
        assertNotEquals(h1, h2);
    }

    @Test
    void testCombine() {
        HashCode h1 = explorer.murmur3("Part1");
        HashCode h2 = explorer.murmur3("Part2");
        HashCode combined = explorer.combine(h1, h2);

        assertNotNull(combined);
        assertNotEquals(h1, combined);
        assertNotEquals(h2, combined);
    }
}
