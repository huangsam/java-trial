package io.huangsam.trial.concurrent.sync;

import org.slf4j.Logger;

public sealed abstract class AbstractRunner implements Runnable
    permits CountRunner, CyclicRunner, SemaphoreRunner {
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
