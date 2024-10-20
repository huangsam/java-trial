package io.huangsam.trial.etl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
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
public class TestNumberRunner {
    @Mock
    private NumberCruncher mockCruncher;

    @Mock
    private NumberReporter mockReporter;

    private static final long TOY_ID = 5L;

    @Test
    void testJobRunsWithoutErrors() {
        Runnable runner = new NumberRunner(TOY_ID, new NumberCruncher(), new NumberReporter());
        assertDoesNotThrow(runner::run);
    }

    @Test
    void testCruncherWithCompleteResult() {
        Runnable runner = new NumberRunner(TOY_ID, new NumberCruncher(), mockReporter);
        runner.run();
        verify(mockReporter).report(squared(TOY_ID), TOY_ID);
    }

    @Test
    void testCruncherWithErrorResult() throws InterruptedException {
        Thread thread = new Thread(new NumberRunner(TOY_ID, new NumberCruncher(), mockReporter));
        thread.start();
        thread.interrupt();
        thread.join();
        verify(mockReporter).report(NumberCruncher.ERROR_RESULT, TOY_ID);
    }

    @Test
    void testCruncherRanMultipleTimes() {
        int expectedThreads = 3;
        List<NumberRunner> runners = Stream.iterate(0L, i -> i < expectedThreads, i -> i + 1)
                .peek(i -> when(mockCruncher.compute(i)).thenReturn(squared(i)))
                .map(i -> new NumberRunner(i, mockCruncher, mockReporter))
                .toList();

        for (Runnable runner : runners) {
            runner.run();
        }

        // Aggregate testing is coarse
        verify(mockCruncher, times(runners.size())).compute(anyLong());
        verify(mockReporter, times(runners.size())).report(anyLong(), anyLong());

        // Individual testing is granular
        for (long i = 0; i < runners.size(); i++) {
            verify(mockCruncher, times(1)).compute(i);
            verify(mockReporter, times(1)).report(squared(i), i);
        }
    }

    private long squared(long input) {
        return input * input;
    }
}
