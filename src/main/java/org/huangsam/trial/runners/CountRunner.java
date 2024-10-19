package org.huangsam.trial.runners;

import java.util.concurrent.CountDownLatch;

public class CountRunner extends AbstractRunner {
    private final CountDownLatch latch;

    public CountRunner(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    void beforeWork() {
    }

    @Override
    void afterWork() {
        log().debug("Run countdown logic");
        latch.countDown();
    }
}
