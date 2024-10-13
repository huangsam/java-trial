package org.huangsam.sample.io;

public class NumberCruncher {
    private static final long BUILTIN_DELAY = 1000L;

    public void compute(int input) {
        try {
            Thread.sleep(BUILTIN_DELAY * input);
        } catch (InterruptedException ignored) {}
    }
}
