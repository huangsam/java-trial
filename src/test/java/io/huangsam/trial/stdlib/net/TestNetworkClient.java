package io.huangsam.trial.stdlib.net;

import com.sun.net.httpserver.HttpServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestNetworkClient {

    private HttpServer server;
    private int port;
    private NetworkClient client;

    @BeforeEach
    void setUp() throws IOException {
        // Port 0 picks an available ephemeral port
        server = HttpServer.create(new InetSocketAddress(0), 0);
        port = server.getAddress().getPort();
        client = new NetworkClient();

        server.createContext("/get", exchange -> {
            byte[] response = "Hello, Client!".getBytes(StandardCharsets.UTF_8);
            exchange.sendResponseHeaders(200, response.length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response);
            }
        });

        server.createContext("/post", exchange -> {
            byte[] requestBody = exchange.getRequestBody().readAllBytes();
            exchange.sendResponseHeaders(200, requestBody.length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(requestBody);
            }
        });

        server.start();
    }

    @AfterEach
    void tearDown() {
        server.stop(0);
    }

    @Test
    void testHttpGetAsync() throws ExecutionException, InterruptedException {
        String url = String.format("http://localhost:%d/get", port);
        CompletableFuture<String> future = client.getAsync(url);
        assertEquals("Hello, Client!", future.get());
    }

    @Test
    void testHttpPostAsync() throws ExecutionException, InterruptedException {
        String url = String.format("http://localhost:%d/post", port);
        String body = "{\"key\": \"value\"}";
        CompletableFuture<String> future = client.postAsync(url, body);
        assertEquals(body, future.get());
    }
}
