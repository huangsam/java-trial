package org.huangsam.sample;

import org.huangsam.sample.numerical.NumberCruncher;
import org.huangsam.sample.numerical.NumberReporter;
import org.huangsam.sample.numerical.NumberTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class TrialRunner {
    private static final Logger LOG = LoggerFactory.getLogger(TrialRunner.class);

    private static final String CFG_NAME = "config.properties";

    private static final String PROP_ENTER = "banner.enter";
    private static final String PROP_EXIT = "banner.exit";

    private static final String EMOJI_HAPPY = ":)";
    private static final String EMOJI_SAD = ":(";

    public static void main(String[] args) throws InterruptedException, IOException {
        Properties config = new Properties();

        // https://www.baeldung.com/java-try-with-resources
        try (InputStream in = TrialRunner.class.getClassLoader().getResourceAsStream(CFG_NAME)) {
            config.load(in);
        }

        String enterMessage = config.getProperty(PROP_ENTER);

        LOG.info(enterMessage);

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

        String exitMessage = config.getProperty(PROP_EXIT);
        String exitEmoji = service.awaitTermination(5L, TimeUnit.SECONDS) ? EMOJI_HAPPY : EMOJI_SAD;

        LOG.info("{} {}", exitMessage, exitEmoji);
    }
}
