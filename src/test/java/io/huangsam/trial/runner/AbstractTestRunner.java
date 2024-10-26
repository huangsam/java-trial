package io.huangsam.trial.runner;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class AbstractTestRunner {
    static final int FEW_COUNT = 3;
    static final int MANY_COUNT = FEW_COUNT * 10;

    Thread countThread(CountDownLatch latch) {
        return new Thread(new CountRunner(latch));
    }

    Thread cyclicThread(CyclicBarrier barrier) {
        return new Thread(new CyclicRunner(barrier));
    }

    Thread semaphoreThread(Semaphore semaphore) {
        return new Thread(new SemaphoreRunner(semaphore));
    }
}
