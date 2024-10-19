package org.huangsam.sample.tasks;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Testing Java concurrency primitives.
 *
 * @see <a href="https://www.baeldung.com/java-util-concurrent">Baeldung on concurrency</a>
 * @see <a href="https://www.baeldung.com/java-countdown-latch">Baeldung on countdown</a>
 * @see <a href="https://www.baeldung.com/java-semaphore">Baeldung on semaphore</a>
 * @see <a href="https://www.baeldung.com/java-blocking-queue">Baeldung on blocking queue</a>
 * @see <a href="https://www.baeldung.com/java-delay-queue">Baeldung on delay queue</a>
 * @see <a href="https://www.baeldung.com/java-concurrent-locks">Baeldung on lock</a>
 * @see <a href="https://www.baeldung.com/java-phaser">Baeldung on phaser</a>
 */
public class TestConcurrency {
    private static final Logger LOG = LoggerFactory.getLogger(TestConcurrency.class);

    private static final int FEW_COUNT = 3;
    private static final int MANY_COUNT = FEW_COUNT * 4;

    @Test
    void testExecutorService() throws InterruptedException, ExecutionException {
        ExecutorService service = Executors.newFixedThreadPool(2);
        Future<Integer> future1 = service.submit(() -> {
            Thread.sleep(100L);
            return 1;
        });
        Future<Integer> future2 = service.submit(() -> {
            Thread.sleep(125L);
            return 2;
        });

        assertEquals(1, future1.get());
        assertEquals(2, future2.get());

        service.shutdown();
    }

    @Test
    void testScheduledExecutorService() throws InterruptedException, ExecutionException {
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        Future<Integer> future = service.schedule(() -> 1, 500L, TimeUnit.MILLISECONDS);

        assertEquals(1, future.get());

        service.shutdown();
    }

    @Test
    void testStartingCountThreads() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(FEW_COUNT);
        List<Thread> workers = Stream
                .generate(() -> countThread(latch))
                .limit(FEW_COUNT)
                .toList();

        assertEquals(FEW_COUNT, latch.getCount());
        assertEquals(FEW_COUNT, workers.size());

        workers.forEach(Thread::start);

        assertNotEquals(0, latch.getCount());

        latch.await();

        assertEquals(0, latch.getCount());
    }

    @Test
    void testStartingCyclicThreads() {
        CyclicBarrier barrier = new CyclicBarrier(FEW_COUNT, () -> LOG.info("Invoke barrier action"));
        List<Thread> workers = Stream
                .generate(() -> cyclicThread(barrier))
                .limit(FEW_COUNT)
                .toList();

        assertEquals(FEW_COUNT, workers.size());

        assertFalse(barrier.isBroken());

        workers.forEach(Thread::start);

        assertFalse(barrier.isBroken());
    }

    @Test
    void testStartingSemaphoreThreads() {
        Semaphore semaphore = new Semaphore(FEW_COUNT);

        assertEquals(FEW_COUNT, semaphore.availablePermits());

        List<Thread> workers = Stream
                .generate(() -> semaThread(semaphore))
                .limit(MANY_COUNT)
                .toList();

        workers.forEach(Thread::start);

        assertEquals(0, semaphore.availablePermits());

        workers.forEach(worker -> {
            try {
                worker.join();
            } catch (InterruptedException e) {
                LOG.error(e.getMessage(), e);
            }
        });

        assertEquals(FEW_COUNT, semaphore.availablePermits());
    }

    private static Thread countThread(CountDownLatch latch) {
        return new Thread(new CountTask(latch));
    }

    private static Thread cyclicThread(CyclicBarrier barrier) {
        return new Thread(new CyclicTask(barrier));
    }

    private static Thread semaThread(Semaphore semaphore) {
        return new Thread(new SemaphoreTask(semaphore));
    }
}
