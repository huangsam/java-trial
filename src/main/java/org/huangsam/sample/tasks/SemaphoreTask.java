package org.huangsam.sample.tasks;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreTask extends AbstractTask {
    private static final long POLL_IN_MS = 125L;

    private final Semaphore semaphore;
    private int attempts = 0;

    public SemaphoreTask(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        boolean acquired = false;
        do {
            try {
                acquired = semaphore.tryAcquire(POLL_IN_MS, TimeUnit.MILLISECONDS);
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
