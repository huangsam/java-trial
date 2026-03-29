package io.huangsam.trial.libs.guava;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.CacheStats;
import com.google.common.cache.LoadingCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Demonstrates Guava's LoadingCache for efficient data retrieval and expiry.
 */
public class CacheManager {
    private static final Logger LOG = LoggerFactory.getLogger(CacheManager.class);

    private final LoadingCache<String, String> cache;

    public CacheManager() {
        this.cache = CacheBuilder.newBuilder()
                .maximumSize(100)
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .recordStats()
                .build(new CacheLoader<>() {
                    @Override
                    public String load(String key) {
                        return simulateExpensiveOperation(key);
                    }
                });
    }

    /**
     * Gets a value from the cache, loading it if not present.
     *
     * @param key the key to retrieve
     * @return the cached or loaded value
     */
    public String getValue(String key) {
        try {
            return cache.get(key);
        } catch (ExecutionException e) {
            LOG.error("Error loading value for key: {}", key, e);
            throw new RuntimeException("Failed to load value", e);
        }
    }

    /**
     * Retrieves the current cache statistics.
     *
     * @return the cache statistics
     */
    public CacheStats getStats() {
        CacheStats stats = cache.stats();
        LOG.info("Current cache statistics: {}", stats);
        return stats;
    }

    /**
     * Explicitly invalidates a key in the cache.
     *
     * @param key the key to invalidate
     */
    public void invalidate(String key) {
        LOG.info("Invalidating cache key: '{}'", key);
        cache.invalidate(key);
    }

    private String simulateExpensiveOperation(String key) {
        LOG.info("Cache miss for key: '{}'. Executing expensive operation...", key);
        // Simulate a delay (e.g., database lookup or API call)
        try {
            Thread.sleep(100); 
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return "Processed:" + key;
    }
}
