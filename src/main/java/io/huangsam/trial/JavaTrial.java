package io.huangsam.trial;

import io.huangsam.trial.concurrent.workload.NumberCruncher;
import io.huangsam.trial.concurrent.workload.NumberReporter;
import io.huangsam.trial.concurrent.workload.NumberRunner;
import io.huangsam.trial.stdlib.net.NetworkClient;
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

public class JavaTrial {
    private static final Logger LOG = LoggerFactory.getLogger(JavaTrial.class);

    public static void main(String[] args) throws InterruptedException, IOException {
        Properties config = new Properties();

        // https://www.baeldung.com/java-try-with-resources
        try (InputStream propertyStream = getPropertyStream()) {
            config.load(propertyStream);
        }

        LOG.info(config.getProperty("banner.enter"));

        NetworkClient network = new NetworkClient();
        network.getAsync("https://www.google.com")
                .thenAccept(body -> LOG.info("Network check (Google): {} bytes received", body.length()))
                .exceptionally(e -> {
                    LOG.error("Network check failed: {}", e.getMessage());
                    return null;
                });

        int threadCount = 4;
        boolean isTerminated;
        try (ExecutorService service = Executors.newVirtualThreadPerTaskExecutor()) {
            NumberCruncher cruncher = new NumberCruncher();
            NumberReporter reporter = new NumberReporter();

            Stream.iterate(1L, i -> i <= threadCount, i -> i + 1)
                    .map(i -> service.submit(new NumberRunner(i, cruncher, reporter)))
                    .forEach(future -> {
                        try {
                            future.get();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        } catch (ExecutionException e) {
                            LOG.error(e.getMessage(), e);
                        }
                    });

            service.shutdown();

            isTerminated = service.awaitTermination(1000L, TimeUnit.MILLISECONDS);
        }

        LOG.info("{} {}", config.getProperty("banner.exit"), isTerminated ? ":)" : ":(");
    }

    private static InputStream getPropertyStream() {
        return JavaTrial.class.getClassLoader().getResourceAsStream("config.properties");
    }
}
