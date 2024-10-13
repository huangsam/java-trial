package org.huangsam.sample.io;

public class NumberFetcher implements Runnable {
    private static final long BUILTIN_DELAY = 1000L;

    private final int id;
    private NumberCruncher cruncher;

    public NumberFetcher(NumberCruncher worker, int input) {
        id = input;
        cruncher = worker;
    }

    @Override
    public void run() {
        cruncher.compute(id);

        System.out.println("Crunched numbers with Thread " + id);
    }
}
