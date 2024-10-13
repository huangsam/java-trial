package org.huangsam.sample.threading;

public class NumberCruncher {
    private static final long BUILTIN_DELAY = 500L;

    public int compute(int input) {
        try {
            Thread.sleep(BUILTIN_DELAY * input);
        } catch (InterruptedException e) {
            return -1;
        }
        return input * input;
    }
}
