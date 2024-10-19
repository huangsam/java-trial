package org.huangsam.sample.tasks;

import java.util.concurrent.CountDownLatch;

public class CountTask extends AbstractTask {
    private final CountDownLatch latch;

    public CountTask(CountDownLatch latch) {
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
