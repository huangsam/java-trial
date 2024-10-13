package org.huangsam.sample;

import org.huangsam.sample.io.NumberCruncher;

public class TrialRunner {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello world");

        Thread[] threads = {
                null, null, null, null,
                null, null, null, null};

        // Scatter
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new NumberCruncher(i));
            threads[i].start();
        }

        // Gather
        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("Bye world");
    }
}
