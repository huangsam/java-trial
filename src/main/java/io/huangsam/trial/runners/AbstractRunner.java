package io.huangsam.trial.runners;

import org.slf4j.Logger;

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

    abstract Logger log();
}
