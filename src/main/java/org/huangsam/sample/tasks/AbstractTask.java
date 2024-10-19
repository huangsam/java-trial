package org.huangsam.sample.tasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractTask implements Runnable {
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
