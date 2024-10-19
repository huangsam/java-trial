package io.huangsam.trial.google;

import com.google.gson.Gson;
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
public class TestGson {
    private static final Gson GSON = new Gson();

    @Test
    void testPrimitivesToJson() {
        assertEquals("1", GSON.toJson(1));
        assertEquals("\"abcd\"", GSON.toJson("abcd"));
        assertEquals("[1]", GSON.toJson(new int[]{1}));
        assertEquals("null", GSON.toJson(null));
    }

    @Test
    void testJsonToPrimitives() {
        assertEquals(1, GSON.fromJson("1", Integer.class));
        assertArrayEquals(new int[]{1, 2}, GSON.fromJson("[1, 2]", int[].class));
    }
}
