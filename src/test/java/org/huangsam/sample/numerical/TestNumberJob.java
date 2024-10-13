package org.huangsam.sample.numerical;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyInt;
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

    @Test
    void testCruncherCompleted() throws InterruptedException {
        int expectedId = 4;

        Thread thread = new Thread(
                new NumberJob(expectedId, new NumberCruncher(), mockReporter));
        thread.start();
        thread.join();

        verify(mockReporter).report(16, expectedId);
    }

    @Test
    void testCruncherInterrupted() {
        int expectedId = 4;

        Thread thread = new Thread(
                new NumberJob(expectedId, new NumberCruncher(), mockReporter));
        thread.start();
        thread.interrupt();

        verify(mockReporter).report(NumberCruncher.ERROR_RESULT, expectedId);
    }

    @Test
    void testCruncherRanMultipleTimes() throws InterruptedException {
        Thread[] threads = {null, null, null, null};
        for (int i = 0; i < threads.length; i++) {
            when(mockCruncher.compute(i)).thenReturn(i * i);
            threads[i] = new Thread(new NumberJob(i, mockCruncher, mockReporter));
        }

        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }

        // Aggregate testing is coarse
        verify(mockCruncher, times(threads.length)).compute(anyInt());
        verify(mockReporter, times(threads.length)).report(anyInt(), anyInt());

        // Individual testing is granular
        for (int i = 0; i < threads.length; i++) {
            verify(mockCruncher, times(1)).compute(i);
            verify(mockReporter, times(1)).report(i * i, i);
        }
    }
}
