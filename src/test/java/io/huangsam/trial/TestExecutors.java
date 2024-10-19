package io.huangsam.trial;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestExecutors {
    @Test
    void testExecutor() throws InterruptedException {
        Invoker invoker = new Invoker();

        invoker.execute(() -> {
        });

        Thread.sleep(100L);

        assertTrue(invoker.isInvoked());
    }

    @Test
    void testExecutorService() throws InterruptedException, ExecutionException {
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
    void testScheduledExecutorService() throws InterruptedException, ExecutionException {
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        ScheduledFuture<Integer> future = service.schedule(() -> 1, 50L, TimeUnit.MILLISECONDS);

        assertEquals(1, future.get());

        assertFalse(service.isTerminated());

        service.shutdown();

        assertTrue(service.awaitTermination(100L, TimeUnit.MILLISECONDS));
        assertTrue(service.isTerminated());
    }

    private static class Invoker implements Executor {
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
