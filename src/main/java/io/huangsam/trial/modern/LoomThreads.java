package io.huangsam.trial.modern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Demonstrates Java 21+ Virtual Threads (Project Loom).
 */
public class LoomThreads {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoomThreads.class);

    /**
     * Runs a large number of tasks using virtual threads.
     *
     * @param taskCount the number of tasks to run
     * @return the number of successfully completed tasks
     * @throws InterruptedException if the execution is interrupted
     */
    public int runManyVirtualThreads(int taskCount) throws InterruptedException {
        AtomicInteger counter = new AtomicInteger(0);
        
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < taskCount; i++) {
                executor.submit(() -> {
                    // Simulate a small blocking I/O operation
                    try {
                        Thread.sleep(10);
                        counter.incrementAndGet();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                });
            }
        }
        // The try-with-resources on ExecutorService will wait for all tasks to finish (Java 19+)
        
        LOGGER.info("Successfully completed {} virtual threads", counter.get());
        return counter.get();
    }
}
