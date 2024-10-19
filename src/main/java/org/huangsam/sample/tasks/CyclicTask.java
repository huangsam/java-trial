package org.huangsam.sample.tasks;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicTask extends AbstractTask {
    private final CyclicBarrier barrier;

    public CyclicTask(CyclicBarrier barrier) {
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
}
