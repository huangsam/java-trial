package io.huangsam.trial.runner;

import org.junit.jupiter.api.RepeatedTest;

import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Let's test how the {@code Semaphore} primitive works!
 *
 * @see <a href="https://www.baeldung.com/java-util-concurrent">Baeldung intro</a>
 * @see <a href="https://www.baeldung.com/java-semaphore">Baeldung on semaphore</a>
 */
public class TestSemaphoreRunner extends AbstractTestRunner {
    @RepeatedTest(3)
    void testStartingSemaphoreThreads() throws InterruptedException {
        Semaphore semaphore = new Semaphore(FEW_COUNT);
        List<Thread> workers = Stream
                .generate(() -> semaphoreThread(semaphore))
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
}
