package io.huangsam.trial.concurrent.patterns;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Systematically explores asynchronous programming with {@link CompletableFuture}.
 * Demonstrates chaining, combining, and error handling.
 */
public class AsyncExplorer {
    /**
     * Constructs an async explorer.
     */
    public AsyncExplorer() {
        // Default constructor
    }

    private final ExecutorService executor = Executors.newFixedThreadPool(4);

    /**
     * Executes a task asynchronously and returns its result when completed.
     *
     * @param value the input value
     * @return a CompletableFuture for the result
     */
    public CompletableFuture<String> supplyAsync(String value) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return value.toUpperCase();
        }, executor);
    }

    /**
     * Chains two asynchronous operations with {@code thenCompose}.
     *
     * @param firstValue  the initial value
     * @param secondValue the value to append
     * @return a CompletableFuture for the composed result
     */
    public CompletableFuture<String> composeAsync(String firstValue, String secondValue) {
        return supplyAsync(firstValue)
                .thenCompose(val -> supplyAsync(val + secondValue));
    }

    /**
     * Combines results of two independent asynchronous operations with {@code thenCombine}.
     *
     * @param a the first value
     * @param b the second value
     * @return a CompletableFuture for combined result
     */
    public CompletableFuture<String> combineAsync(String a, String b) {
        CompletableFuture<String> futureA = supplyAsync(a);
        CompletableFuture<String> futureB = supplyAsync(b);
        return futureA.thenCombine(futureB, (resA, resB) -> resA + " " + resB);
    }

    /**
     * Executes multiple tasks and waits for all of them to complete with {@code allOf}.
     *
     * @param values list of input values
     * @return a CompletableFuture representing the completion of all tasks
     */
    public CompletableFuture<List<String>> allOfAsync(List<String> values) {
        List<CompletableFuture<String>> futures = values.stream()
                .map(this::supplyAsync)
                .collect(Collectors.toList());

        CompletableFuture<Void> allFutures = CompletableFuture.allOf(
                futures.toArray(new CompletableFuture[0])
        );

        return allFutures.thenApply(v -> futures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList())
        );
    }

    /**
     * Demonstrates exception handling in CompletableFuture with {@code exceptionally}.
     *
     * @return a CompletableFuture that handles an exception
     */
    public CompletableFuture<String> handleExceptionAsync() {
        return CompletableFuture.<String>supplyAsync(() -> {
            throw new RuntimeException("Async error");
        }, executor).exceptionally(ex -> "Recovered from " + ex.getMessage());
    }

    /**
     * Shuts down the internal executor.
     */
    public void shutdown() {
        executor.shutdown();
        try {
            if (!executor.awaitTermination(1, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }
    }
}
