package io.huangsam.trial.guava;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nullable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Google Guava is a useful set of utilities and data structures.
 *
 * @see <a href="https://www.baeldung.com/guava-futures-listenablefuture">Baeldung on futures</a>
 */
public class TestListenableFuture {
    public static final Logger LOG = LoggerFactory.getLogger(TestListenableFuture.class);

    @Test
    void testListenableBasic() throws ExecutionException, InterruptedException {
        ListeningExecutorService service = getListeningService();

        ListenableFuture<Integer> future = service.submit(() -> 1);

        assertEquals(1, future.get());

        service.shutdown();
    }

    @Test
    void testListenableCallback() throws ExecutionException, InterruptedException {
        ListeningExecutorService service = getListeningService();

        ListenableFuture<Integer> future = service.submit(() -> 1);

        Executor listeningExecutor = Executors.newSingleThreadExecutor();
        Futures.addCallback(future, new FutureCallback<>() {
            @Override
            public void onSuccess(Integer result) {
                LOG.info("We succeeded with: {}", result);
            }

            @Override
            public void onFailure(@Nullable Throwable t) {
                if (t != null) {
                    LOG.warn("Nothing good is happening: {}", t.getMessage());
                }
            }
        }, listeningExecutor);

        assertEquals(1, future.get());

        service.shutdown();
    }

    @Test
    void testListenableFanIn() throws ExecutionException, InterruptedException {
        ListeningExecutorService service = getListeningService();

        ListenableFuture<Integer> idFuture = service.submit(() -> 1);
        ListenableFuture<String> nameFuture = service.submit(() -> "Bob");

        Executor listeningExecutor = Executors.newSingleThreadExecutor();
        ListenableFuture<String> allInFuture = Futures.whenAllSucceed(idFuture, nameFuture)
                .call(() -> Futures.getDone(nameFuture) + " " + Futures.getDone(idFuture), listeningExecutor);

        assertEquals("Bob 1", allInFuture.get());

        service.shutdown();
    }

    private ListeningExecutorService getListeningService() {
        return MoreExecutors.listeningDecorator(Executors.newSingleThreadExecutor());
    }
}
