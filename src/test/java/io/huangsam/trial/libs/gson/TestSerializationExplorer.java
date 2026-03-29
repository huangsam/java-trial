package io.huangsam.trial.libs.gson;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests basic JSON serialization and deserialization operations from the Google Gson
 * library. The other popular library is Jackson.
 *
 * @see <a href="https://github.com/google/gson/blob/main/UserGuide.md">Gson guide</a>
 * @see <a href="https://github.com/FasterXML/jackson-docs">Jackson guide</a>
 */
public class TestSerializationExplorer {
    private final SerializationExplorer explorer = new SerializationExplorer();

    @Test
    void testPrimitivesToJson() {
        assertEquals("1", explorer.toJson(1));
        assertEquals("\"abcd\"", explorer.toJson("abcd"));
        assertEquals("[1]", explorer.toJson(new int[]{1}));
        assertEquals("null", explorer.toJson(null));
    }

    @Test
    void testJsonToPrimitives() {
        assertEquals(1, explorer.fromJson("1", Integer.class));
        assertEquals(false, explorer.fromJson("false", Boolean.class));
        assertArrayEquals(new int[]{1, 2}, explorer.fromJson("[1, 2]", int[].class));
    }
}
