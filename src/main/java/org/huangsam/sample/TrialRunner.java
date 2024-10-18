package org.huangsam.sample;

import org.huangsam.sample.numerical.NumberCruncher;
import org.huangsam.sample.numerical.NumberReporter;
import org.huangsam.sample.numerical.NumberTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class TrialRunner {
    private static final Logger LOG = LoggerFactory.getLogger(TrialRunner.class);

    public static void main(String[] args) throws InterruptedException {
        LOG.info("Hello world");

        int threadCount = 5;

        ExecutorService service = Executors.newFixedThreadPool(threadCount);

        NumberCruncher cruncher = new NumberCruncher();
        NumberReporter reporter = new NumberReporter();

        Stream.iterate(0, i -> i < threadCount, i -> i + 1)
                .map(i -> service.submit(new NumberTask(i, cruncher, reporter)))
                .forEach(future -> {
                    try {
                        Object result = future.get();
                        if (result != null) {
                            LOG.warn("Expected nothing but got {}", result);
                        }
                    } catch (InterruptedException | ExecutionException e) {
                        LOG.error(e.getMessage(), e);
                    }
                });

        service.shutdown();

        if (service.awaitTermination(5L, TimeUnit.SECONDS)) {
            LOG.info("Bye world :)");
        } else {
            LOG.warn("Bye world :(");
        }
    }
}
