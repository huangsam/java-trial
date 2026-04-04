package io.huangsam.trial.concurrent.sync;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * A runner that performs a cyclic barrier operation.
 */
public final class CyclicRunner extends AbstractRunner {
    private static final Logger LOG = LoggerFactory.getLogger(CyclicRunner.class);

    private final CyclicBarrier barrier;

    /**
     * Constructs a cyclic runner.
     *
     * @param barrier the barrier
     */
    public CyclicRunner(CyclicBarrier barrier) {
        this.barrier = barrier;
    }

    @Override
    void beforeWork() {
        log().debug("Wait for barrier");
    }

    @Override
    void afterWork() {
        try {
            int arrivalIndex = barrier.await();
            log().debug("Run after barrier arrived with index {}", arrivalIndex);
        } catch (InterruptedException | BrokenBarrierException e) {
            throw new RuntimeException("Abort during barrier blockage", e);
        }
    }

    @Override
    Logger log() {
        return LOG;
    }
}
