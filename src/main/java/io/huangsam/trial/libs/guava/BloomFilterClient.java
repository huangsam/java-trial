package io.huangsam.trial.libs.guava;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.nio.charset.StandardCharsets;

/**
 * Demonstrates Guava's BloomFilter for probabilistic membership testing.
 * Bloom filters are space-efficient and can tell you if an element is NOT in a set
 * with 100% certainty, or if it MIGHT be in a set with a configurable false positive rate.
 */
@SuppressWarnings("UnstableApiUsage")
public class BloomFilterClient {

    private final BloomFilter<String> filter;

    /**
     * Initializes a BloomFilter with an expected number of insertions and a false positive probability.
     *
     * @param expectedInsertions the number of items we expect to store
     * @param fpp                the desired false positive probability (e.g., 0.01 for 1%)
     */
    public BloomFilterClient(long expectedInsertions, double fpp) {
        this.filter = BloomFilter.create(
                Funnels.stringFunnel(StandardCharsets.UTF_8),
                expectedInsertions,
                fpp
        );
    }

    /**
     * Adds an item to the Bloom filter.
     *
     * @param item the item to add
     */
    public void put(String item) {
        filter.put(item);
    }

    /**
     * Checks if the Bloom filter might contain the item.
     *
     * @param item the item to check
     * @return true if the item might be in the filter, false if it is definitely not
     */
    public boolean mightContain(String item) {
        return filter.mightContain(item);
    }

    /**
     * Gets the current false positive probability based on the number of inserted items.
     *
     * @return the expected FPP
     */
    public double expectedFpp() {
        return filter.expectedFpp();
    }
}
