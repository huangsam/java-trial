package org.huangsam.sample.threading;

public class NumberJob implements Runnable {
    private final int id;
    private final NumberCruncher cruncher;

    public NumberJob(NumberCruncher worker, int input) {
        id = input;
        cruncher = worker;
    }

    @Override
    public void run() {
        cruncher.compute(id);

        System.out.println("Crunched numbers with Thread " + id);
    }
}
