package org.huangsam.sample;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
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
        CountDownLatch latch = new CountDownLatch(5);
        List<Thread> workers = Stream.generate(() -> new Thread(new CountWorker(latch))).limit(5).toList();
        workers.forEach(Thread::start);
        latch.await();
        assertEquals(0, latch.getCount());
    }

    @Test
    void testCyclicBarrier() {
        Thread[] threads = {null, null, null};

        CyclicBarrier barrier = new CyclicBarrier(threads.length, () -> LOG.info("All tasks are completed"));

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new CyclicWorker(barrier));
        }

        if (!barrier.isBroken()) {
            Arrays.stream(threads).forEach(Thread::start);
        }
    }

    private record CountWorker(CountDownLatch latch) implements Runnable {
        @Override
        public void run() {
            LOG.debug("Run countdown logic");
            latch.countDown();
        }
    }

    private record CyclicWorker(CyclicBarrier barrier) implements Runnable {
        @Override
        public void run() {
            try {
                LOG.debug("Wait for barrier");
                barrier.await();
                LOG.debug("Barrier is released");
            } catch (InterruptedException | BrokenBarrierException e) {
                LOG.error(e.getMessage(), e);
            }
        }
    }
}
