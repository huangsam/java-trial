package org.huangsam.sample.threading;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TestNumberJob {
    @Mock
    private NumberCruncher mockCruncher;

    @Test
    void testCruncherRanInJob() throws InterruptedException {
        Thread thread = new Thread(new NumberJob(0, mockCruncher));
        thread.start();
        thread.join();
        verify(mockCruncher).compute(0);
    }

    @Test
    void testCruncherRanMultipleTimes() throws InterruptedException {
        Thread[] threads = {
                new Thread(new NumberJob(0, mockCruncher)),
                new Thread(new NumberJob(1, mockCruncher)),
                new Thread(new NumberJob(2, mockCruncher)),
                new Thread(new NumberJob(3, mockCruncher))};
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        verify(mockCruncher, times(threads.length)).compute(anyInt());
    }
}
