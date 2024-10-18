package org.huangsam.sample.google;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
