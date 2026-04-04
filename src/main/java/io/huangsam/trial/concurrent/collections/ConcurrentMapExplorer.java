package io.huangsam.trial.concurrent.collections;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Explores thread-safe collections in Java.
 * Demonstrates ConcurrentHashMap and its atomic operations.
 */
public class ConcurrentMapExplorer {

    private final ConcurrentMap<String, Integer> map = new ConcurrentHashMap<>();

    /**
     * Puts a value in the map if it's not present.
     *
     * @param key   the map key
     * @param value the default value
     * @return current value associated with the key
     */
    public Integer putIfAbsent(String key, Integer value) {
        return map.putIfAbsent(key, value);
    }

    /**
     * Atomically computes a new value for a key using its current value.
     *
     * @param key   the map key
     * @param delta the amount to increment the value
     * @return new value associated with the key
     */
    public Integer increment(String key, int delta) {
        return map.compute(key, (k, v) -> v == null ? delta : v + delta);
    }

    /**
     * Retrieves a value from the map.
     *
     * @param key the map key
     * @return current value, or null if not found
     */
    public Integer getValue(String key) {
        return map.get(key);
    }

    /**
     * Clears the map.
     */
    public void clear() {
        map.clear();
    }

    /**
     * Returns the size of the map.
     *
     * @return map size
     */
    public int size() {
        return map.size();
    }
}
