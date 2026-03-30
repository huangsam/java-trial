package io.huangsam.trial.libs.gson;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TestJsonParser {

    private final JsonParserExplorer explorer = new JsonParserExplorer();

    @Test
    void testParseBasicJson() {
        String json = "{\"name\": \"John\", \"age\": 30}";
        assertEquals("John", explorer.getStringValue(json, "name"));
        assertEquals("30", explorer.getStringValue(json, "age"));
        assertNull(explorer.getStringValue(json, "foo"));
    }

    @Test
    void testParseNestedJson() {
        String json = "{\"user\": {\"id\": \"123\", \"email\": \"john@example.com\"}, \"status\": \"active\"}";
        assertEquals("123", explorer.getNestedValue(json, "user", "id"));
        assertEquals("john@example.com", explorer.getNestedValue(json, "user", "email"));
        assertNull(explorer.getNestedValue(json, "user", "foo"));
        assertNull(explorer.getNestedValue(json, "foo", "bar"));
    }
}
