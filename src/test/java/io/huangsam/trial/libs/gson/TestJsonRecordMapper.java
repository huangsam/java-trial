package io.huangsam.trial.libs.gson;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestJsonRecordMapper {

    @Test
    void testRecordSerialization() {
        JsonRecordMapper mapper = new JsonRecordMapper();
        JsonRecordMapper.DigitalAsset btc = new JsonRecordMapper.DigitalAsset("BTC", 65000.0);
        
        String json = mapper.toJson(btc);
        assertTrue(json.contains("\"symbol\":\"BTC\""));
        assertTrue(json.contains("\"price\":65000.0"));
        
        JsonRecordMapper.DigitalAsset deserialized = mapper.fromJson(json);
        assertEquals(btc, deserialized);
        assertEquals("BTC", deserialized.symbol());
        assertEquals(65000.0, deserialized.price());
    }
}
