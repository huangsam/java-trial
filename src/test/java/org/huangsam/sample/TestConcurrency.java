package org.huangsam.sample;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
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

    private static final long SLEEP_IN_MS = 250L;

    @Test
    void testExecutorService() throws InterruptedException, ExecutionException {
        ExecutorService service = Executors.newFixedThreadPool(2);
        Future<Integer> future1 = service.submit(() -> {
            Thread.sleep(SLEEP_IN_MS);
            return 1;
        });
        Future<Integer> future2 = service.submit(() -> {
            Thread.sleep(SLEEP_IN_MS);
            return 2;
        });

        assertEquals(1, future1.get());
        assertEquals(2, future2.get());

        service.shutdown();
    }

    @Test
    void testScheduledExecutorService() throws InterruptedException, ExecutionException {
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        Future<Integer> future = service.schedule(() -> 1, SLEEP_IN_MS, TimeUnit.MILLISECONDS);

        assertEquals(1, future.get());

        service.shutdown();
    }

    @Test
    void testCountDownLatch() throws InterruptedException {
        int expectedWorkers = 3;

        CountDownLatch latch = new CountDownLatch(expectedWorkers);
        List<Thread> workers = Stream
                .generate(() -> countThread(latch))
                .limit(expectedWorkers)
                .toList();

        assertEquals(expectedWorkers, latch.getCount());
        assertEquals(expectedWorkers, workers.size());

        workers.forEach(Thread::start);

        assertNotEquals(0, latch.getCount());

        latch.await();

        assertEquals(0, latch.getCount());
    }

    @Test
    void testCyclicBarrier() {
        int expectedWorkers = 3;

        CyclicBarrier barrier = new CyclicBarrier(expectedWorkers, () -> LOG.info("All tasks are completed"));
        List<Thread> workers = Stream
                .generate(() -> cyclicThread(barrier))
                .limit(expectedWorkers)
                .toList();

        assertEquals(expectedWorkers, workers.size());

        assertFalse(barrier.isBroken());

        workers.forEach(Thread::start);

        assertFalse(barrier.isBroken());
    }

    private static Thread countThread(CountDownLatch latch) {
        return new Thread(new CountTask(latch));
    }

    private static Thread cyclicThread(CyclicBarrier barrier) {
        return new Thread(new CyclicTask(barrier));
    }

    private record CountTask(CountDownLatch latch) implements Runnable {
        @Override
        public void run() {
            LOG.debug("Run countdown logic");
            latch.countDown();
        }
    }

    private record CyclicTask(CyclicBarrier barrier) implements Runnable {
        @Override
        public void run() {
            try {
                LOG.debug("Wait for barrier");
                barrier.await();
                LOG.debug("Run after barrier");
            } catch (InterruptedException | BrokenBarrierException e) {
                LOG.error(e.getMessage(), e);
            }
        }
    }
}
