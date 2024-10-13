package org.huangsam.sample.threading;

public class NumberJob implements Runnable {
    private final int id;
    private final NumberCruncher cruncher;
    private final NumberReporter reporter;

    public NumberJob(int id, NumberCruncher cruncher, NumberReporter reporter) {
        this.id = id;
        this.cruncher = cruncher;
        this.reporter = reporter;
    }

    @Override
    public void run() {
        reporter.report(cruncher.compute(id), id);
    }
}
