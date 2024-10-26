package io.huangsam.trial.runner;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.CyclicBarrier;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Let's test how the {@code CyclicBarrier} primitive works!
 *
 * @see <a href="https://www.baeldung.com/java-util-concurrent">Baeldung intro</a>
 * @see <a href="https://www.baeldung.com/java-cyclic-barrier">Baeldung on cyclic</a>
 */
public class TestCyclicRunner extends AbstractTestRunner {
    private static final Logger LOG = LoggerFactory.getLogger(TestCyclicRunner.class);

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
}
