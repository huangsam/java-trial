package io.huangsam.trial.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicRunner extends AbstractRunner {
    private static final Logger LOG = LoggerFactory.getLogger(CyclicRunner.class);

    private final CyclicBarrier barrier;

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
