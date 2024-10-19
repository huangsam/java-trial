package io.huangsam.trial.runner;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestRunners {
    private static final Logger LOG = LoggerFactory.getLogger(TestRunners.class);

    private static final int FEW_COUNT = 3;
    private static final int MANY_COUNT = FEW_COUNT * 4;

    @Test
    void testStartingCountThreads() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(FEW_COUNT);
        Stream.generate(() -> countThread(latch))
                .limit(FEW_COUNT)
                .forEach(Thread::start);

        assertNotEquals(0, latch.getCount());

        latch.await();

        assertEquals(0, latch.getCount());
    }

    @Test
    void testStartingCyclicThreads() throws InterruptedException {
        CyclicBarrier barrier = new CyclicBarrier(FEW_COUNT, () -> LOG.info("Invoke barrier action"));
        List<Thread> workers = Stream
                .generate(() -> cyclicThread(barrier))
                .limit(FEW_COUNT)
                .peek(Thread::start)
                .toList();

        assertEquals(FEW_COUNT, workers.size());

        for (Thread worker : workers) {
            worker.join();
        }

        assertTrue(workers.stream().noneMatch(Thread::isAlive));
    }

    @RepeatedTest(3)
    void testStartingSemaphoreThreads() throws InterruptedException {
        Semaphore semaphore = new Semaphore(FEW_COUNT);
        List<Thread> workers = Stream
                .generate(() -> semaThread(semaphore))
                .limit(MANY_COUNT)
                .peek(Thread::start)
                .toList();

        assertEquals(0, semaphore.availablePermits());
        assertNotEquals(0, semaphore.getQueueLength());

        for (Thread worker : workers) {
            worker.join();
        }

        assertEquals(FEW_COUNT, semaphore.availablePermits());
        assertEquals(0, semaphore.getQueueLength());
    }

    private static Thread countThread(CountDownLatch latch) {
        return new Thread(new CountRunner(latch));
    }

    private static Thread cyclicThread(CyclicBarrier barrier) {
        return new Thread(new CyclicRunner(barrier));
    }

    private static Thread semaThread(Semaphore semaphore) {
        return new Thread(new SemaphoreRunner(semaphore));
    }
}
