package io.huangsam.trial.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

public class CountRunner extends AbstractRunner {
    private static final Logger LOG = LoggerFactory.getLogger(CountRunner.class);

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

    @Override
    Logger log() {
        return LOG;
    }
}
