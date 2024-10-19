package io.huangsam.trial.etl;

public class NumberCruncher {
    public static final long ERROR_RESULT = -1L;

    public Long compute(long input) {
        try {
            Thread.sleep(250L * input);
        } catch (InterruptedException e) {
            return ERROR_RESULT;
        }
        return input * input;
    }
}
