package io.huangsam.trial.delivery;

import org.junit.jupiter.api.RepeatedTest;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * An involved example of the product-consumer pattern. We have more consumers
 * than producers. Producers will create enough sentinel values such that the
 * consumers will stop.
 *
 * @see <a href="https://www.baeldung.com/java-blocking-queue">Baeldung on BlockingQueue</a>
 */
public class TestProducerConsumer {
    private static final int BOUND = 10;
    private static final int PRODUCER_COUNT = 2;
    private static final int CONSUMER_COUNT = 4;
    private static final int PILL_VALUE = Integer.MAX_VALUE;
    private static final int PILL_RATIO = CONSUMER_COUNT / PRODUCER_COUNT;

    @RepeatedTest(3)
    void testBlockingQueueIsEmpty() throws InterruptedException {
        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(BOUND);

        ExecutorService producerService = Executors.newFixedThreadPool(PRODUCER_COUNT);
        ExecutorService consumerService = Executors.newFixedThreadPool(CONSUMER_COUNT);

        Stream.generate(() -> new NumbersProducer(queue, PILL_VALUE, PILL_RATIO))
                .limit(PRODUCER_COUNT)
                .forEach(producerService::submit);

        Stream.generate(() -> new NumbersConsumer(queue, PILL_VALUE))
                .limit(CONSUMER_COUNT)
                .forEach(consumerService::submit);

        producerService.shutdown();
        consumerService.shutdown();

        assertTrue(producerService.awaitTermination(100L, TimeUnit.MILLISECONDS));
        assertTrue(consumerService.awaitTermination(100L, TimeUnit.MILLISECONDS));

        assertEquals(0, queue.size());
    }
}
