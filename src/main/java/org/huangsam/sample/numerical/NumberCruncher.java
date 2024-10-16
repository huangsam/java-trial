package org.huangsam.sample.numerical;

public class NumberCruncher {
    public static final int ERROR_RESULT = -1;
    private static final long BUILTIN_DELAY = 250L;

    public int compute(int input) {
        try {
            Thread.sleep(BUILTIN_DELAY * input);
        } catch (InterruptedException e) {
            return ERROR_RESULT;
        }
        return input * input;
    }
}
