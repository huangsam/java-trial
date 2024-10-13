package org.huangsam.sample;

import org.huangsam.sample.io.NumberCruncher;
import org.huangsam.sample.io.NumberFetcher;

public class TrialRunner {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello world");

        Thread[] threads = {
                null, null, null, null,
                null, null, null, null};

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(
                    new NumberFetcher(new NumberCruncher(), i));
            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("Bye world");
    }
}
