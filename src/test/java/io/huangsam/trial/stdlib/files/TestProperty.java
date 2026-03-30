package io.huangsam.trial.stdlib.files;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestProperty {

    private final PropertyExplorer explorer = new PropertyExplorer();

    @Test
    void testLoadProperties() throws IOException {
        Properties props = explorer.loadFromResources("app.properties");

        assertEquals("Java Trial", props.getProperty("app.name"));
        assertEquals("21", props.getProperty("app.version"));
        assertEquals("active", props.getProperty("app.status"));
    }

    @Test
    void testGetPropertyWithDefault() throws IOException {
        Properties props = explorer.loadFromResources("app.properties");

        assertEquals("localhost", explorer.getProperty(props, "db.host", "127.0.0.1"));
        assertEquals("3306", explorer.getProperty(props, "db.mysql.port", "3306"));
    }
}
