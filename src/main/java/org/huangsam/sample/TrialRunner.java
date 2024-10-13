package org.huangsam.sample;

import org.huangsam.sample.numerical.NumberCruncher;
import org.huangsam.sample.numerical.NumberJob;
import org.huangsam.sample.numerical.NumberReporter;

public class TrialRunner {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello world");

        Thread[] threads = {null, null, null, null, null, null};

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(
                    new NumberJob(i, new NumberCruncher(), new NumberReporter()));
            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("Bye world");
    }
}
