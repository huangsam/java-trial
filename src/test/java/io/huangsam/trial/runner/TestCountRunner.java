package io.huangsam.trial.runner;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Let's test how the {@code CountDownLatch} primitive works!
 *
 * @see <a href="https://www.baeldung.com/java-util-concurrent">Baeldung intro</a>
 * @see <a href="https://www.baeldung.com/java-countdown-latch">Baeldung on countdown</a>
 */
public class TestCountRunner extends AbstractTestRunner {
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
}
