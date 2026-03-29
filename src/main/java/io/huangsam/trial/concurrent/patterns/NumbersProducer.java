package io.huangsam.trial.concurrent.patterns;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadLocalRandom;

/**
 * A producer that generates random numbers and adds them to a blocking queue.
 *
 * @param numbersQueue          the queue to add numbers to
 * @param poisonPill            the value representing a poison pill
 * @param poisonPillPerProducer the number of poison pills to produce
 */
public record NumbersProducer(BlockingQueue<Integer> numbersQueue, int poisonPill, int poisonPillPerProducer) implements Runnable {
    /**
     * Executes the producer logic.
     */
    public void run() {
        try {
            generateNumbers();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void generateNumbers() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            numbersQueue.put(ThreadLocalRandom.current().nextInt(100));
        }
        for (int j = 0; j < poisonPillPerProducer; j++) {
            numbersQueue.put(poisonPill);
        }
    }
}
