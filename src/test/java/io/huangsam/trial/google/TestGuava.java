package io.huangsam.trial.google;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Lists;
import com.google.common.collect.Multiset;
import com.google.common.collect.Range;
import com.google.common.collect.RangeSet;
import com.google.common.collect.TreeRangeSet;
import com.google.common.math.LongMath;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nullable;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Google Guava is a useful set of utilities and data structures.
 *
 * @see <a href="https://www.baeldung.com/guava-multiset">Baeldung on Multiset</a>
 * @see <a href="https://www.baeldung.com/guava-rangeset">Baeldung on RangeSet</a>
 * @see <a href="https://www.baeldung.com/guava-futures-listenablefuture">Baeldung on futures</a>
 */
public class TestGuava {
    public static final Logger LOG = LoggerFactory.getLogger(TestGuava.class);

    @Test
    void testMultiSet() {
        Multiset<String> bookStore = HashMultiset.create();

        String key = "Potter";

        bookStore.add(key);
        bookStore.add(key);
        bookStore.add(key);

        assertTrue(bookStore.contains(key));
        assertEquals(3, bookStore.count(key));

        bookStore.remove(key);

        assertEquals(2, bookStore.count(key));

        int expectedCount = 50;
        bookStore.setCount(key, expectedCount);

        assertEquals(expectedCount, bookStore.count(key));

        int expectedNewCount = 100;

        assertTrue(bookStore.setCount(key, expectedCount, expectedNewCount));
        assertFalse(bookStore.setCount(key, expectedCount, expectedNewCount));

        assertThrows(IllegalArgumentException.class, () -> bookStore.setCount(key, -1));
    }

    @Test
    void testRangeSet() {
        RangeSet<Integer> numberRangeSet = TreeRangeSet.create();

        numberRangeSet.add(Range.closed(0, 2));
        numberRangeSet.add(Range.closed(3, 5));
        numberRangeSet.add(Range.closed(6, 8));

        assertTrue(numberRangeSet.contains(1));
        assertFalse(numberRangeSet.contains(9));

        numberRangeSet.clear();
        numberRangeSet.add(Range.closed(0, 5));
        numberRangeSet.remove(Range.closed(1, 4));
        assertTrue(numberRangeSet.contains(0));
        assertFalse(numberRangeSet.contains(1));
        assertFalse(numberRangeSet.contains(4));
        assertTrue(numberRangeSet.contains(5));

        numberRangeSet.clear();
        numberRangeSet.add(Range.closed(0, 2));
        numberRangeSet.add(Range.closed(3, 5));
        numberRangeSet.add(Range.closed(6, 8));

        Range<Integer> span = numberRangeSet.span();
        assertEquals(0, span.lowerEndpoint().intValue());
        assertEquals(8, span.upperEndpoint().intValue());
    }

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

    @Test
    void testStringWithJoiner() {
        List<String> names = Lists.newArrayList("Alice", "Bob", "Charlie");
        String joinedNames = Joiner.on(", ").join(names);
        assertEquals("Alice, Bob, Charlie", joinedNames);
    }

    @Test
    void testStringWithSplitter() {
        String sentence = "This is a sample sentence.";
        List<String> words = Splitter.on(' ').splitToList(sentence);
        assertTrue(words.stream().noneMatch(word -> word.contains(" ")));
    }

    @Test
    void testSomeLongMath() {
        assertEquals(120L, LongMath.factorial(5));
        assertTrue(LongMath.isPowerOfTwo(16L));
    }

    private ListeningExecutorService getListeningService() {
        return MoreExecutors.listeningDecorator(Executors.newSingleThreadExecutor());
    }
}
