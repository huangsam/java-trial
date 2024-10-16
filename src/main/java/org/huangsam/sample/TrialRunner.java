package org.huangsam.sample;

import org.huangsam.sample.numerical.NumberCruncher;
import org.huangsam.sample.numerical.NumberJob;
import org.huangsam.sample.numerical.NumberReporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TrialRunner {
    private static final Logger LOG = LoggerFactory.getLogger(TrialRunner.class);

    public static void main(String[] args) throws InterruptedException {
        LOG.info("Hello world");

        Thread[] threads = {null, null, null, null, null, null};

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new NumberJob(i, new NumberCruncher(), new NumberReporter()));
            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        LOG.info("Bye world");
    }
}
