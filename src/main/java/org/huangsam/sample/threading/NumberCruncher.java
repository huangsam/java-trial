package org.huangsam.sample.threading;

public class NumberCruncher {
    private static final long BUILTIN_DELAY = 500L;

    public void compute(int input) {
        try {
            Thread.sleep(BUILTIN_DELAY * input);
        } catch (InterruptedException ignored) {}
    }
}
