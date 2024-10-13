package org.huangsam.sample.threading;

public class NumberCruncher {
    public static final int ERROR_RESULT = -1;
    private static final long BUILTIN_DELAY = 500L;

    public int compute(int input) {
        try {
            Thread.sleep(BUILTIN_DELAY * input);
        } catch (InterruptedException e) {
            return ERROR_RESULT;
        }
        return input * input;
    }
}
