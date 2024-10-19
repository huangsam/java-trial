package io.huangsam.trial.runners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreRunner extends AbstractRunner {
    private static final Logger LOG = LoggerFactory.getLogger(SemaphoreRunner.class);

    private static final int MAX_ATTEMPTS = 10;

    private final Semaphore semaphore;
    private int attempts = 0;

    public SemaphoreRunner(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        if (acquireSemaphore()) {
            super.run();
        } else {
            log().warn("Cannot acquire semaphore after {} attempts", attempts);
        }
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

    @Override
    Logger log() {
        return LOG;
    }

    private boolean acquireSemaphore() {
        boolean result = false;
        do {
            try {
                long timeout = (long) (50.0 * Math.pow(1.5, attempts - 1));
                result = semaphore.tryAcquire(timeout, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                log().error(e.getMessage(), e);
            }

            attempts++;
        } while (!result && attempts < MAX_ATTEMPTS);
        return result;
    }
}
