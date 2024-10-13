package org.huangsam.sample.threading;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestNumberJob {
    @Mock
    private NumberCruncher mockCruncher;

    @Mock
    private NumberReporter mockReporter;

    @Test
    void testCruncherRanInJob() throws InterruptedException {
        int expectedId = 4;
        int expectedResult = 16;

        when(mockCruncher.compute(expectedId)).thenReturn(expectedResult);

        Thread thread = new Thread(new NumberJob(expectedId, mockCruncher, mockReporter));
        thread.start();
        thread.join();

        verify(mockCruncher).compute(expectedId);
        verify(mockReporter).report(expectedResult, expectedId);
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

        verify(mockCruncher, times(threads.length)).compute(anyInt());
        verify(mockReporter, times(threads.length)).report(anyInt(), anyInt());

        verify(mockCruncher, times(1)).compute(3);
        verify(mockReporter, times(1)).report(9, 3);
    }
}
