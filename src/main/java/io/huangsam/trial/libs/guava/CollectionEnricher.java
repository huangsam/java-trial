package io.huangsam.trial.libs.guava;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Multimap;

import java.util.Collection;

/**
 * Demonstrates advanced Guava collections: Multimap and BiMap.
 */
public class CollectionEnricher {

    /**
     * Demonstrates a Multimap, which maps one key to multiple values.
     * Useful for grouping tags, categories, or interests.
     *
     * @return a multimap of user interests
     */
    public Multimap<String, String> createUserInterests() {
        Multimap<String, String> userInterests = ArrayListMultimap.create();
        userInterests.put("Alice", "Java");
        userInterests.put("Alice", "Guava");
        userInterests.put("Bob", "Python");
        userInterests.put("Bob", "Machine Learning");
        userInterests.put("Charlie", "Java");
        return userInterests;
    }

    /**
     * Gets interests for a specific user.
     *
     * @param map the multimap of interests
     * @param user the user name
     * @return the collection of interests
     */
    public Collection<String> getInterests(Multimap<String, String> map, String user) {
        return map.get(user);
    }

    /**
     * Demonstrates a BiMap, which ensures a unique mapping in both directions.
     * Useful for bidirectional unique lookups (e.g., ID to Username).
     *
     * @return a bidirectional mapping of user IDs and names
     */
    public BiMap<Integer, String> createUserIdMap() {
        BiMap<Integer, String> userIdMap = HashBiMap.create();
        userIdMap.put(101, "Alice");
        userIdMap.put(102, "Bob");
        userIdMap.put(103, "Charlie");
        return userIdMap;
    }

    /**
     * Gets a user name by ID.
     *
     * @param map the BIMAP of IDs to names
     * @param id the user ID
     * @return the user name
     */
    public String getUserName(BiMap<Integer, String> map, Integer id) {
        return map.get(id);
    }

    /**
     * Gets a user ID by name.
     *
     * @param map the BIMAP of IDs to names
     * @param name the user name
     * @return the user ID
     */
    public Integer getUserId(BiMap<Integer, String> map, String name) {
        return map.inverse().get(name);
    }
}
