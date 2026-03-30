package io.huangsam.trial.libs.guava;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Demonstrates Guava's EventBus for decoupled, asynchronous-like internal messaging.
 * Uses a publish-subscribe pattern within a single JVM.
 */
public class EventBusManager {

    private static final Logger LOG = LoggerFactory.getLogger(EventBusManager.class);

    private final EventBus bus;

    public EventBusManager(String busName) {
        this.bus = new EventBus(busName);
    }

    /**
     * Registers a listener object to receive events.
     *
     * @param listener the object with @Subscribe methods
     */
    public void register(Object listener) {
        bus.register(listener);
    }

    /**
     * Posts an event to all registered subscribers.
     *
     * @param event the event to post
     */
    public void post(Object event) {
        bus.post(event);
    }

    /**
     * A sample subscriber that logs events and tracks them for testing.
     */
    public static class SampleSubscriber {

        private final List<Object> events = new ArrayList<>();

        @Subscribe
        public void onMessage(String msg) {
            LOG.info("Subscriber received String: {}", msg);
            events.add(msg);
        }

        @Subscribe
        public void onInteger(Integer val) {
            LOG.info("Subscriber received Integer: {}", val);
            events.add(val);
        }

        public List<Object> getEvents() {
            return new ArrayList<>(events);
        }
    }
}
