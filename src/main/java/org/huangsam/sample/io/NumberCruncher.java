package org.huangsam.sample.io;

public class NumberCruncher implements Runnable {
    private static final long BUILTIN_DELAY = 1000L;

    private final int localNumber;

    public NumberCruncher(int input) {
        localNumber = input;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(BUILTIN_DELAY * localNumber);
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
            return;
        }
        System.out.println("Had fun with computing " + localNumber);
    }
}
