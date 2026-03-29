package io.huangsam.trial.stdlib.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Utility for loading and querying Java Properties files.
 */
public class PropertyExplorer {

    /**
     * Loads a properties file from the classpath.
     *
     * @param name the property resource name
     * @return the loaded Properties object
     * @throws IOException if the resource cannot be read
     */
    public Properties loadFromResources(String name) throws IOException {
        Properties props = new Properties();
        try (InputStream stream = getClass().getClassLoader().getResourceAsStream(name)) {
            if (stream == null) {
                throw new IllegalArgumentException("Property file not found: " + name);
            }
            props.load(stream);
        }
        return props;
    }

    /**
     * Retrieves a property value with a default fallback.
     *
     * @param props        the properties object
     * @param key          the key to lookup
     * @param defaultValue the default value if the key is missing
     * @return the property value
     */
    public String getProperty(Properties props, String key, String defaultValue) {
        return props.getProperty(key, defaultValue);
    }
}
