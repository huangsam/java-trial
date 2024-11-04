package io.huangsam.trial;

import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * {@code Executor}, and its family of classes, are the easiest for adopting async logic.
 *
 * @see <a href="https://www.baeldung.com/java-util-concurrent">Baeldung intro</a>
 * @see <a href="https://www.baeldung.com/java-executor-service-tutorial">Baeldung on service</a>
 */
public class TestExecutor {
    @Test
    void testSerialExecutorIsInvoked() {
        SerialInvoker invoker = new SerialInvoker();

        assertFalse(invoker.isInvoked());

        invoker.execute(() -> {
        });

        assertTrue(invoker.isInvoked());
    }

    @Test
    void testExecutorServiceIsFinished() throws InterruptedException, ExecutionException {
        ExecutorService service = Executors.newFixedThreadPool(2);
        Future<Integer> future1 = service.submit(() -> {
            Thread.sleep(50L);
            return 1;
        });
        Future<Integer> future2 = service.submit(() -> {
            Thread.sleep(50L);
            return 2;
        });

        assertEquals(1, future1.get());
        assertEquals(2, future2.get());

        assertFalse(service.isTerminated());

        service.shutdown();

        assertTrue(service.awaitTermination(100L, TimeUnit.MILLISECONDS));

        assertTrue(service.isTerminated());
    }

    @Test
    void testExecutorServiceIsStuck() throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(2);
        service.submit(() -> {
            Thread.sleep(1000000L);
            return 1;
        });

        assertFalse(service.isTerminated());

        service.shutdown();

        assertFalse(service.awaitTermination(100L, TimeUnit.MILLISECONDS));

        assertFalse(service.isTerminated());
    }

    @Test
    void testScheduledServiceIsFinished() throws InterruptedException, ExecutionException {
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        ScheduledFuture<Integer> future = service.schedule(() -> 1, 50L, TimeUnit.MILLISECONDS);

        assertEquals(1, future.get());

        assertFalse(service.isTerminated());

        service.shutdown();

        assertTrue(service.awaitTermination(100L, TimeUnit.MILLISECONDS));

        assertTrue(service.isTerminated());
    }

    @Test
    void testPutKeysInParallel() throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(10);
        Map<Integer, Integer> map = new ConcurrentHashMap<>();
        Stream.iterate(0, i -> i < 100, i -> i + 1)
                .forEach(i -> service.submit(() -> {
                    map.put(i, i * 3);
                }));
        service.shutdown();
        assertTrue(service.awaitTermination(100L, TimeUnit.MILLISECONDS));
        assertFalse(map.isEmpty());
        assertEquals(100, map.size());
        assertTrue(map.entrySet().stream().allMatch(entry -> entry.getValue() % 3 == 0));
    }

    private static class SerialInvoker implements Executor {
        private Boolean invoked = false;

        @Override
        public void execute(Runnable runner) {
            runner.run();
            invoked = true;
        }

        public boolean isInvoked() {
            return invoked;
        }
    }
}
