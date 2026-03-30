package io.huangsam.trial.libs.guava;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SuppressWarnings("UnstableApiUsage")
public class TestBloomFilterClient {

    @Test
    void testBloomFilterProbabilities() {
        // Create a filter with a 1% false positive rate
        BloomFilterClient client = new BloomFilterClient(100, 0.01);

        // Add 100 items
        for (int i = 0; i < 100; i++) {
            client.put("Item-" + i);
        }

        // Verify all 100 items are "might be in" (guaranteed)
        for (int i = 0; i < 100; i++) {
            assertTrue(client.mightContain("Item-" + i));
        }

        // Check something definitely NOT in there
        // Note: There is a 0.1% chance this might actually return true due to FPP
        assertFalse(client.mightContain("Non-Existent-Item-123456789"));
    }

    @Test
    void testExpectedFpp() {
        BloomFilterClient client = new BloomFilterClient(1000, 0.03);
        assertTrue(client.expectedFpp() <= 0.03);
    }
}
