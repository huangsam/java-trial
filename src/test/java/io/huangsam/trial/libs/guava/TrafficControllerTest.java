package io.huangsam.trial.libs.guava;

import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TrafficControllerTest {

    @Test
    void testRateLimitBlocking() {
        // Rate limit of 10 permits per second
        TrafficController controller = new TrafficController(10.0);
        AtomicInteger count = new AtomicInteger(0);

        // Run 5 tasks. With 10 permits/sec, these should be fast.
        for (int i = 0; i < 5; i++) {
            controller.runThrottled(count::incrementAndGet);
        }
        assertEquals(5, count.get());
    }

    @Test
    void testTryAcquire() {
        // Very low rate limit
        TrafficController controller = new TrafficController(0.1);
        AtomicInteger count = new AtomicInteger(0);

        // First one should succeed
        boolean first = controller.tryRun(count::incrementAndGet);
        assertTrue(first);
        assertEquals(1, count.get());

        // Second one immediately should fail
        boolean second = controller.tryRun(count::incrementAndGet);
        assertFalse(second);
        assertEquals(1, count.get());
    }
}
