package io.huangsam.trial.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreRunner extends AbstractRunner {
    private static final Logger LOG = LoggerFactory.getLogger(SemaphoreRunner.class);

    private static final int MAX_ATTEMPTS = 5;

    private final Semaphore semaphore;
    private final Backoff backOff;
    private int attempts = 0;

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
                long timeout = backOff.next();
                result = semaphore.tryAcquire(timeout, TimeUnit.MILLISECONDS);
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
