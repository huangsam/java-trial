package io.huangsam.trial.concurrent.sync;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for {@link LockExplorer}.
 */
public class TestLockExplorer {

    private LockExplorer explorer;

    @BeforeEach
    void setUp() {
        explorer = new LockExplorer();
    }

    @Test
    void testReentrantLock() throws InterruptedException {
        int threads = 10;
        int iterations = 1000;
        ExecutorService executor = Executors.newFixedThreadPool(threads);

        for (int i = 0; i < threads * iterations; i++) {
            executor.submit(explorer::incrementWithReentrantLock);
        }

        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);

        assertEquals(threads * iterations, explorer.getCounter());
    }

    @Test
    void testReadWriteLock() throws InterruptedException {
        int threads = 10;
        int iterations = 1000;
        ExecutorService executor = Executors.newFixedThreadPool(threads);

        for (int i = 0; i < threads * iterations; i++) {
            executor.submit(explorer::incrementWithWriteLock);
        }

        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);

        assertEquals(threads * iterations, explorer.getWithReadLock());
    }

    @Test
    void testReset() {
        explorer.incrementWithReentrantLock();
        explorer.reset();
        assertEquals(0, explorer.getCounter());
    }
}
