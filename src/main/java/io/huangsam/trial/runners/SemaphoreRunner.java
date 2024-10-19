package io.huangsam.trial.runners;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreRunner extends AbstractRunner {
    private static int MAX_ATTEMPTS = 10;

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
                acquired = semaphore.tryAcquire(getTimeout(), TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                log().error(e.getMessage(), e);
            }

            attempts++;

            if (attempts == MAX_ATTEMPTS) {
                log().warn("Failed to acquire semaphore after {} attempts", attempts);
                return;
            }
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

    private long getTimeout() {
        return (long) (50.0 * Math.pow(2, attempts - 1));
    }
}
