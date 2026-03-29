package io.huangsam.trial.libs.guava;

import com.google.common.util.concurrent.RateLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Demonstrates Guava's RateLimiter for flow control.
 */
@SuppressWarnings("UnstableApiUsage")
public class TrafficController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TrafficController.class);

    private final RateLimiter rateLimiter;

    public TrafficController(double permitsPerSecond) {
        this.rateLimiter = RateLimiter.create(permitsPerSecond);
    }

    /**
     * Executes an operation, blocking if necessary to adhere to the rate limit.
     *
     * @param task the operation to run
     */
    public void runThrottled(Runnable task) {
        double waitTime = rateLimiter.acquire();
        if (waitTime > 0.0) {
            LOGGER.info("Throttled: waited {} seconds", waitTime);
        }
        task.run();
    }

    /**
     * Tries to acquire a permit without blocking.
     *
     * @param task the operation to run
     * @return true if the task was executed, false otherwise
     */
    public boolean tryRun(Runnable task) {
        if (rateLimiter.tryAcquire()) {
            task.run();
            return true;
        } else {
            LOGGER.warn("Operation skipped: rate limit exceeded");
            return false;
        }
    }
}
