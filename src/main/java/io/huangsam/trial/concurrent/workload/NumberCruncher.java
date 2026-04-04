package io.huangsam.trial.concurrent.workload;

/**
 * Simulates a heavy numerical computation.
 */
public class NumberCruncher {
    /**
     * Error result returned when computation is interrupted.
     */
    public static final long ERROR_RESULT = -1L;

    /**
     * Constructs a number cruncher.
     */
    public NumberCruncher() {
        // Default constructor
    }

    /**
     * Computes the square of the input after a simulated delay.
     *
     * @param input the value to square
     * @return the squared value, or ERROR_RESULT if interrupted
     */
    public Long compute(long input) {
        try {
            Thread.sleep(250L * input);
        } catch (InterruptedException e) {
            return ERROR_RESULT;
        }
        return input * input;
    }
}
