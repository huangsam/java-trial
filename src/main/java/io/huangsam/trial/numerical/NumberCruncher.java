package io.huangsam.trial.numerical;

public class NumberCruncher {
    public static final long ERROR_RESULT = -1L;
    private static final long BUILTIN_DELAY = 250L;

    public Long compute(long input) {
        try {
            Thread.sleep(BUILTIN_DELAY * input);
        } catch (InterruptedException e) {
            return ERROR_RESULT;
        }
        return input * input;
    }
}
