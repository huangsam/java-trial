package io.huangsam.trial.libs.gson;

import com.google.gson.Gson;

public class JsonExplorer {
    private static final Gson GSON = new Gson();

    /**
     * Serializes an object to JSON.
     *
     * @param src the object to serialize
     * @return the JSON string representation of the object
     */
    public String toJson(Object src) {
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
        return GSON.fromJson(json, classOfT);
    }
}
