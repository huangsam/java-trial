package io.huangsam.trial.libs.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Demonstrates the integration of GSON with modern Java Records.
 */
public class JsonRecordMapper {
    private static final Logger LOG = LoggerFactory.getLogger(JsonRecordMapper.class);
    private final Gson gson;

    /**
     * A simple record representing a digital asset.
     *
     * @param symbol the asset symbol (e.g., BTC)
     * @param price  the current price
     */
    public record DigitalAsset(String symbol, double price) {
    }

    /**
     * Constructs a JSON record mapper.
     */
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
        if (asset == null) {
            LOG.info("Serializing null DigitalAsset");
        } else {
            LOG.info("Serializing DigitalAsset: {}", asset.symbol());
        }
        return gson.toJson(asset);
    }

    /**
     * Deserializes JSON back into a Record.
     *
     * @param json the JSON string
     * @return the deserialized DigitalAsset record
     */
    public DigitalAsset fromJson(String json) {
        LOG.info("Deserializing JSON to DigitalAsset");
        LOG.debug("Raw JSON: {}", json);
        return gson.fromJson(json, DigitalAsset.class);
    }
}
