package io.huangsam.trial.libs.guava;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestEventBusManager {

    @Test
    void testEventBusDispatch() {
        EventBusManager manager = new EventBusManager("trial-bus");
        EventBusManager.SampleSubscriber subscriber = new EventBusManager.SampleSubscriber();
        manager.register(subscriber);

        manager.post("Hello EventBus!");
        manager.post(42);

        List<Object> events = subscriber.getEvents();
        assertEquals(2, events.size());
        assertTrue(events.contains("Hello EventBus!"));
        assertTrue(events.contains(42));
    }
}
