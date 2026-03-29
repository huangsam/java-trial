package io.huangsam.trial.concurrent.workload;

public record NumberRunner(Long input, NumberCruncher cruncher, NumberReporter reporter) implements Runnable {
    @Override
    public void run() {
        reporter.report(cruncher.compute(input), input);
    }
}
