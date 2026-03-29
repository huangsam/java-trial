package io.huangsam.trial.libs.guava;

import com.google.common.collect.BiMap;
import com.google.common.collect.Multimap;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestCollectionExplorer {

    @Test
    void testMultimapInterests() {
        CollectionExplorer explorer = new CollectionExplorer();
        Multimap<String, String> map = explorer.createUserInterests();

        Collection<String> aliceInterests = explorer.getInterests(map, "Alice");
        assertEquals(2, aliceInterests.size(), "Alice should have 2 interests");
        assertTrue(aliceInterests.contains("Java"));
        assertTrue(aliceInterests.contains("Guava"));

        Collection<String> charlieInterests = explorer.getInterests(map, "Charlie");
        assertEquals(1, charlieInterests.size(), "Charlie should have 1 interest");
    }

    @Test
    void testBiMapBidirectional() {
        CollectionExplorer explorer = new CollectionExplorer();
        BiMap<Integer, String> userIdMap = explorer.createUserIdMap();

        assertEquals("Alice", explorer.getUserName(userIdMap, 101));
        assertEquals(101, explorer.getUserId(userIdMap, "Alice"));

        // Demonstrate uniqueness: BiMap throws error if we try to map a new key to an existing value
        assertThrows(IllegalArgumentException.class, () -> userIdMap.put(104, "Alice"));
    }
}
