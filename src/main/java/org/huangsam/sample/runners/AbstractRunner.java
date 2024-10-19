package org.huangsam.sample.runners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractRunner implements Runnable {
    @Override
    public void run() {
        try {
            beforeWork();
            doWork();
            afterWork();
        } catch (Exception e) {
            log().error(e.getMessage(), e);
        }
    }

    abstract void beforeWork();

    void doWork() throws InterruptedException {
        Thread.sleep(250L);
    }

    abstract void afterWork();

    Logger log() {
        return LoggerFactory.getLogger(getClass());
    }
}
