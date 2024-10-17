package org.huangsam.sample.numerical;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Here we are testing how Mockito works in a pure Java setting.
 * We are mainly trying out core static methods such as
 * {@code verify} and {@code when}.
 *
 * @see <a href="https://www.baeldung.com/mockito-junit-5-extension">Baeldung post</a>
 * @see <a href="https://www.digitalocean.com/community/tutorials/mockito-verify">DO post</a>
 */
@ExtendWith(MockitoExtension.class)
public class TestNumberJob {
    @Mock
    private NumberCruncher mockCruncher;

    @Mock
    private NumberReporter mockReporter;

    private long squared(int input) {
        return (long) input * input;
    }

    @Test
    void testCruncherWithCompleteResult() throws InterruptedException {
        int expectedId = 4;

        Thread thread = new Thread(
                new NumberJob(expectedId, new NumberCruncher(), mockReporter));
        thread.start();

        thread.join();
        assertFalse(thread.isAlive());

        verify(mockReporter).report(squared(expectedId), expectedId);
    }

    @Test
    void testCruncherWithErrorResult() throws InterruptedException {
        int expectedId = 5;

        Thread thread = new Thread(
                new NumberJob(expectedId, new NumberCruncher(), mockReporter));
        thread.start();

        thread.interrupt();
        assertTrue(thread.isInterrupted());
        assertTrue(thread.isAlive());

        thread.join();
        assertFalse(thread.isInterrupted());
        assertFalse(thread.isAlive());

        verify(mockReporter).report(NumberCruncher.ERROR_RESULT, expectedId);
    }

    @Test
    void testCruncherRanMultipleTimes() throws InterruptedException {
        Thread[] threads = {null, null, null, null};
        for (int i = 0; i < threads.length; i++) {
            when(mockCruncher.compute(i)).thenReturn(squared(i));
            threads[i] = new Thread(new NumberJob(i, mockCruncher, mockReporter));
        }

        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }

        // Aggregate testing is coarse
        verify(mockCruncher, times(threads.length)).compute(anyLong());
        verify(mockReporter, times(threads.length)).report(anyLong(), anyInt());

        // Individual testing is granular
        for (int i = 0; i < threads.length; i++) {
            verify(mockCruncher, times(1)).compute(i);
            verify(mockReporter, times(1)).report(squared(i), i);
        }
    }
}
