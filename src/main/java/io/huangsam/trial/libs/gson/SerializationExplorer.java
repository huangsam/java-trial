package io.huangsam.trial.libs.gson;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Demonstrates JSON serialization and deserialization using GSON.
 */
public class SerializationExplorer {
    private static final Logger LOG = LoggerFactory.getLogger(SerializationExplorer.class);
    private static final Gson GSON = new Gson();

    /**
     * Serializes an object to JSON.
     *
     * @param src the object to serialize
     * @return the JSON string representation of the object
     */
    public String toJson(Object src) {
        if (src == null) {
            LOG.info("Serializing null object");
        } else {
            LOG.info("Serializing object of type: {}", src.getClass().getName());
        }
        return GSON.toJson(src);
    }

    /**
     * Deserializes a JSON string into an object of the specified class.
     *
     * @param json the JSON string
     * @param classOfT the class of the object to deserialize
     * @param <T> the type of the object
     * @return the deserialized object
     */
    public <T> T fromJson(String json, Class<T> classOfT) {
        LOG.info("Deserializing JSON to type: {}", classOfT.getName());
        LOG.debug("Raw JSON: {}", json);
        return GSON.fromJson(json, classOfT);
    }
}
