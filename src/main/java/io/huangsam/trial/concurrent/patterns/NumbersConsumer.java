package io.huangsam.trial.concurrent.patterns;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BlockingQueue;

/**
 * A consumer that retrieves numbers from a blocking queue.
 */
public class NumbersConsumer implements Runnable {
    private static final Logger LOG = LoggerFactory.getLogger(NumbersConsumer.class);

    private final BlockingQueue<Integer> queue;
    private final int poisonPill;

    /**
     * Creates a new consumer with the specified queue and poison pill.
     *
     * @param queue      the queue to take numbers from
     * @param poisonPill the value representing a poison pill
     */
    public NumbersConsumer(BlockingQueue<Integer> queue, int poisonPill) {
        this.queue = queue;
        this.poisonPill = poisonPill;
    }

    /**
     * Executes the consumer logic.
     */
    @Override
    public void run() {
        try {
            while (true) {
                Integer number = queue.take();
                if (number.equals(poisonPill)) {
                    LOG.warn("Got poison");
                    return;
                }
                LOG.info("Got result {}", number);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
