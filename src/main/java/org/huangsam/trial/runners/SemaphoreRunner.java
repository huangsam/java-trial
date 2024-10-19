package org.huangsam.trial.runners;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreRunner extends AbstractRunner {
    private final Semaphore semaphore;
    private int attempts = 0;

    public SemaphoreRunner(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        boolean acquired = false;
        do {
            try {
                acquired = semaphore.tryAcquire(50L, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                log().error(e.getMessage(), e);
            }
            attempts++;
        } while (!acquired);

        super.run();
    }

    @Override
    void beforeWork() {
        log().debug("Acquire semaphore after {} attempts", attempts);
    }

    @Override
    void afterWork() {
        log().debug("Release semaphore");
        semaphore.release();
    }
}
