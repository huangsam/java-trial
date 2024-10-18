package org.huangsam.sample.numerical;

public class NumberTask implements Runnable {
    private final int id;
    private final NumberCruncher cruncher;
    private final NumberReporter reporter;

    public NumberTask(int id, NumberCruncher cruncher, NumberReporter reporter) {
        this.id = id;
        this.cruncher = cruncher;
        this.reporter = reporter;
    }

    @Override
    public void run() {
        reporter.report(cruncher.compute(id), id);
    }
}
