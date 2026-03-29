package io.huangsam.trial.guava;

import com.google.common.cache.CacheStats;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CacheExplorerTest {

    @Test
    void testCacheHitAndMiss() {
        CacheExplorer explorer = new CacheExplorer();
        
        // Initial state: empty cache
        String val1 = explorer.getValue("key1");
        assertEquals("Processed:key1", val1);
        
        CacheStats statsAfterMiss = explorer.getStats();
        assertEquals(0, statsAfterMiss.hitCount(), "Should have 0 hits initially");
        assertEquals(1, statsAfterMiss.missCount(), "Should have 1 miss initially");

        // Request again: should be a hit
        String val2 = explorer.getValue("key1");
        assertEquals("Processed:key1", val2);
        
        CacheStats statsAfterHit = explorer.getStats();
        assertEquals(1, statsAfterHit.hitCount(), "Should have 1 hit now");
        assertEquals(1, statsAfterHit.missCount(), "Miss count should remain at 1");
    }

    @Test
    void testInvalidate() {
        CacheExplorer explorer = new CacheExplorer();
        
        // Load key1
        explorer.getValue("key1");
        
        // Invalidate key1
        explorer.invalidate("key1");
        
        // Request again: should be a miss again
        explorer.getValue("key1");
        
        CacheStats stats = explorer.getStats();
        assertEquals(2, stats.missCount(), "Should have 2 misses total after invalidation");
    }
}
