package io.huangsam.trial.libs.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Demonstrates the integration of GSON with modern Java Records.
 */
public class JsonRecordMapper {
    private final Gson gson;

    /**
     * A simple record representing a digital asset.
     *
     * @param symbol the asset symbol (e.g., BTC)
     * @param price  the current price
     */
    public record DigitalAsset(String symbol, double price) {
    }

    public JsonRecordMapper() {
        this.gson = new GsonBuilder().create();
    }

    /**
     * Serializes a Record to JSON.
     *
     * @param asset the record to serialize
     * @return the JSON string
     */
    public String toJson(DigitalAsset asset) {
        return gson.toJson(asset);
    }

    /**
     * Deserializes JSON back into a Record.
     *
     * @param json the JSON string
     * @return the deserialized DigitalAsset record
     */
    public DigitalAsset fromJson(String json) {
        return gson.fromJson(json, DigitalAsset.class);
    }
}
