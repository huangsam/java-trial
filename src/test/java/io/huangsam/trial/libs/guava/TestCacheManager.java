package io.huangsam.trial.libs.guava;

import com.google.common.cache.CacheStats;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCacheManager {

    @Test
    void testCacheHitAndMiss() {
        CacheManager manager = new CacheManager();
        
        // Initial state: empty cache
        String val1 = manager.getValue("key1");
        assertEquals("Processed:key1", val1);
        
        CacheStats statsAfterMiss = manager.getStats();
        assertEquals(0, statsAfterMiss.hitCount(), "Should have 0 hits initially");
        assertEquals(1, statsAfterMiss.missCount(), "Should have 1 miss initially");

        // Request again: should be a hit
        String val2 = manager.getValue("key1");
        assertEquals("Processed:key1", val2);
        
        CacheStats statsAfterHit = manager.getStats();
        assertEquals(1, statsAfterHit.hitCount(), "Should have 1 hit now");
        assertEquals(1, statsAfterHit.missCount(), "Miss count should remain at 1");
    }

    @Test
    void testInvalidate() {
        CacheManager manager = new CacheManager();
        
        // Load key1
        manager.getValue("key1");
        
        // Invalidate key1
        manager.invalidate("key1");
        
        // Request again: should be a miss again
        manager.getValue("key1");
        
        CacheStats stats = manager.getStats();
        assertEquals(2, stats.missCount(), "Should have 2 misses total after invalidation");
    }
}
