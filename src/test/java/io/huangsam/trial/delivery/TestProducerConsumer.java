package io.huangsam.trial.delivery;

import org.junit.jupiter.api.RepeatedTest;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestProducerConsumer {
    private static final int BOUND = 10;
    private static final int PRODUCER_COUNT = 2;
    private static final int CONSUMER_COUNT = 4;
    private static final int PILL_VALUE = Integer.MAX_VALUE;
    private static final int PILL_RATIO = CONSUMER_COUNT / PRODUCER_COUNT;

    @RepeatedTest(3)
    void testBlockingQueue() throws InterruptedException {
        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(BOUND);

        List<Thread> producers = Stream.iterate(0, i -> i < PRODUCER_COUNT, i -> i + 1)
                .map(i -> new Thread(new NumbersProducer(queue, PILL_VALUE, PILL_RATIO)))
                .peek(Thread::start)
                .toList();

        List<Thread> consumers = Stream.iterate(0, j -> j < CONSUMER_COUNT, j -> j + 1)
                .map(j -> new Thread(new NumbersConsumer(queue, PILL_VALUE)))
                .peek(Thread::start)
                .toList();

        for (Thread producer : producers) {
            producer.join();
        }

        for (Thread consumer : consumers) {
            consumer.join();
        }

        assertEquals(0, queue.size());

        assertTrue(producers.stream().noneMatch(Thread::isAlive));
        assertTrue(consumers.stream().noneMatch(Thread::isAlive));
    }
}
