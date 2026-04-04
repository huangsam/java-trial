package io.huangsam.trial.concurrent.workload;

/**
 * A task that crunches a number and reports the result.
 *
 * @param input the input value
 * @param cruncher the cruncher instance
 * @param reporter the reporter instance
 */
public record NumberRunner(Long input, NumberCruncher cruncher, NumberReporter reporter) implements Runnable {
    @Override
    public void run() {
        reporter.report(cruncher.compute(input), input);
    }
}
