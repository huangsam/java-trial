package io.huangsam.trial.concurrent.sync;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * A runner that uses a Semaphore for synchronization with backoff-based retries.
 */
public final class SemaphoreRunner extends AbstractRunner {
    private static final Logger LOG = LoggerFactory.getLogger(SemaphoreRunner.class);

    private static final int MAX_ATTEMPTS = 5;

    private final Semaphore semaphore;
    private final Backoff backOff;
    private int attempts = 0;

    /**
     * Constructs a semaphore runner.
     *
     * @param semaphore the semaphore to use
     */
    public SemaphoreRunner(Semaphore semaphore) {
        this.semaphore = semaphore;
        this.backOff = new Backoff(50.0, 1.25);
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
                result = semaphore.tryAcquire(backOff.next(), TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            attempts++;
        } while (!result && attempts < MAX_ATTEMPTS);
        return result;
    }

    private static class Backoff {
        private double base;
        private final double factor;

        /**
         * Constructs a backoff strategy.
         *
         * @param base the base delay
         * @param factor the multiplication factor
         */
        Backoff(double base, double factor) {
            this.base = base;
            this.factor = factor;
        }

        long next() {
            long timeout = (long) base;
            base *= factor;
            return timeout;
        }
    }
}
