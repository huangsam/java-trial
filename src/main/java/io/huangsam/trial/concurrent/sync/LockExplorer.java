package io.huangsam.trial.concurrent.sync;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Demonstrates explicit lock mechanisms in Java.
 * Explores ReentrantLock and ReentrantReadWriteLock.
 */
public class LockExplorer {

    private final ReentrantLock reentrantLock = new ReentrantLock();
    private final ReadWriteLock rwLock = new ReentrantReadWriteLock();
    private int counter = 0;

    /**
     * Constructs a lock explorer instance.
     */
    public LockExplorer() {
        // Default constructor
    }

    /**
     * Increments the counter using a ReentrantLock.
     * Ensure thread safety with explicit lock/unlock.
     */
    public void incrementWithReentrantLock() {
        reentrantLock.lock();
        try {
            counter++;
        } finally {
            reentrantLock.unlock();
        }
    }

    /**
     * Increments the counter using a WriteLock.
     */
    public void incrementWithWriteLock() {
        rwLock.writeLock().lock();
        try {
            counter++;
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    /**
     * Reads the counter value with a ReadLock.
     * Multiple threads can read simultaneously.
     *
     * @return current counter value
     */
    public int getWithReadLock() {
        rwLock.readLock().lock();
        try {
            return counter;
        } finally {
            rwLock.readLock().unlock();
        }
    }

    /**
     * Resets the counter.
     */
    public void reset() {
        reentrantLock.lock();
        try {
            counter = 0;
        } finally {
            reentrantLock.unlock();
        }
    }

    /**
     * Returns the raw counter value (no lock, for testing with locks externally).
     *
     * @return current counter value
     */
    public int getCounter() {
        return counter;
    }
}
