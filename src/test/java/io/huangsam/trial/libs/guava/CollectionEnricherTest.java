package io.huangsam.trial.libs.guava;

import com.google.common.collect.BiMap;
import com.google.common.collect.Multimap;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CollectionEnricherTest {

    @Test
    void testMultimapInterests() {
        CollectionEnricher enricher = new CollectionEnricher();
        Multimap<String, String> map = enricher.createUserInterests();

        Collection<String> aliceInterests = enricher.getInterests(map, "Alice");
        assertEquals(2, aliceInterests.size(), "Alice should have 2 interests");
        assertTrue(aliceInterests.contains("Java"));
        assertTrue(aliceInterests.contains("Guava"));

        Collection<String> charlieInterests = enricher.getInterests(map, "Charlie");
        assertEquals(1, charlieInterests.size(), "Charlie should have 1 interest");
    }

    @Test
    void testBiMapBidirectional() {
        CollectionEnricher enricher = new CollectionEnricher();
        BiMap<Integer, String> userIdMap = enricher.createUserIdMap();

        assertEquals("Alice", enricher.getUserName(userIdMap, 101));
        assertEquals(101, enricher.getUserId(userIdMap, "Alice"));

        // Demonstrate uniqueness: BiMap throws error if we try to map a new key to an existing value
        assertThrows(IllegalArgumentException.class, () -> userIdMap.put(104, "Alice"));
    }
}
