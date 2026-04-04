package io.huangsam.trial.stdlib.net;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;

/**
 * Demonstrates modern Java HttpClient (Java 11+).
 * Handles asynchronous GET and POST requests using CompletableFuture.
 */
public class NetworkClient {

    private final HttpClient client;
    private final Duration requestTimeout;

    public NetworkClient() {
        this(Duration.ofSeconds(5));
    }

    public NetworkClient(Duration requestTimeout) {
        this.requestTimeout = requestTimeout;
        this.client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(5))
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();
    }

    /**
     * Performs an asynchronous GET request.
     *
     * @param url the destination URL
     * @return a CompletableFuture with the response body
     */
    public CompletableFuture<String> getAsync(String url) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(requestTimeout)
                .GET()
                .build();

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body);
    }

    /**
     * Performs an asynchronous POST request.
     *
     * @param url  the destination URL
     * @param body the request body
     * @return a CompletableFuture with the response body
     */
    public CompletableFuture<String> postAsync(String url, String body) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .timeout(requestTimeout)
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body);
    }
}
