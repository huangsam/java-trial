package io.huangsam.trial.etl;

public class NumberRunner implements Runnable {
    private final Long input;
    private final NumberCruncher cruncher;
    private final NumberReporter reporter;

    public NumberRunner(Long input, NumberCruncher cruncher, NumberReporter reporter) {
        this.input = input;
        this.cruncher = cruncher;
        this.reporter = reporter;
    }

    @Override
    public void run() {
        reporter.report(cruncher.compute(input), input);
    }
}
