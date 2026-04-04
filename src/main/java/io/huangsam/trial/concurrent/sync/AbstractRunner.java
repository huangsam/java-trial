package io.huangsam.trial.concurrent.sync;

import org.slf4j.Logger;

/**
 * Abstract base class for concurrent task runners.
 * Demonstrates the Template Method pattern for executing work.
 */
public sealed abstract class AbstractRunner implements Runnable
    permits CountRunner, CyclicRunner, SemaphoreRunner {

    /**
     * Constructs an abstract runner.
     */
    protected AbstractRunner() {
        // Default constructor
    }
    @Override
    public void run() {
        try {
            beforeWork();
            doWork();
            afterWork();
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        }
    }

    abstract void beforeWork();

    void doWork() throws InterruptedException {
        log().info("Run core business logic");
        Thread.sleep(250L);
    }

    abstract void afterWork();

    abstract Logger log();
}
