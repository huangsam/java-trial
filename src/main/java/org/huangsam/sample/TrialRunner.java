package org.huangsam.sample;

import org.huangsam.sample.numerical.NumberCruncher;
import org.huangsam.sample.numerical.NumberReporter;
import org.huangsam.sample.numerical.NumberTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class TrialRunner {
    private static final Logger LOG = LoggerFactory.getLogger(TrialRunner.class);

    private static final String CONFIG_LOCATION = "./src/main/resources/config.properties";

    public static void main(String[] args) throws InterruptedException, IOException {
        Properties config = new Properties();

        config.load(new FileInputStream(CONFIG_LOCATION));

        String helloString = (String) config.get("helloString");
        String byeString = (String) config.get("byeString");

        LOG.info(helloString);

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

        String emoji = service.awaitTermination(5L, TimeUnit.SECONDS) ? ":)" : ":(";

        LOG.info("{} {}", byeString, emoji);
    }
}
