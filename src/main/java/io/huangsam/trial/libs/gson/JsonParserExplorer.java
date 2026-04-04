package io.huangsam.trial.libs.gson;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Demonstrates the use of Gson's JsonParser for navigating JSON as a tree.
 * This is useful for dealing with dynamic or unknown JSON structures where
 * a full POJO mapping is not required or possible.
 */
public class JsonParserExplorer {
    /**
     * Constructs a JSON parser explorer.
     */
    public JsonParserExplorer() {
        // Default constructor
    }

    /**
     * Parses a JSON string and extracts a specific string value by key path.
     *
     * @param json the JSON string
     * @param key  the key to look up in the root object
     * @return the string value of the key
     */
    public String getStringValue(String json, String key) {
        JsonElement element = JsonParser.parseString(json);
        if (element.isJsonObject()) {
            JsonObject obj = element.getAsJsonObject();
            if (obj.has(key)) {
                return obj.get(key).getAsString();
            }
        }
        return null;
    }

    /**
     * Navigates a nested JSON structure.
     *
     * @param json   the JSON string
     * @param parent the parent key
     * @param child  the child key within the parent
     * @return the string value of the nested key
     */
    public String getNestedValue(String json, String parent, String child) {
        JsonElement root = JsonParser.parseString(json);
        if (root.isJsonObject()) {
            JsonElement parentElement = root.getAsJsonObject().get(parent);
            if (parentElement != null && parentElement.isJsonObject()) {
                JsonElement childElement = parentElement.getAsJsonObject().get(child);
                if (childElement != null) {
                    return childElement.getAsString();
                }
            }
        }
        return null;
    }
}
