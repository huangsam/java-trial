package io.huangsam.trial.collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * A {@code Deque} serves the purpose of a stack and a queue. Getting familiar with
 * its methods can prove useful when you need the flexibility of both data
 * structures.
 */
public class TestDeque {
    private final Deque<Integer> deque = new ArrayDeque<>();

    @BeforeEach
    void resetDeque() {
        deque.clear();
        Stream.of(1, 2, 3).forEach(deque::addLast);
    }

    @Test
    void testDequeSize() {
        assertEquals(3, deque.size());
    }

    @Test
    void testDequePeekFirstAndLast() {
        assertEquals(1, deque.peekFirst());
        assertEquals(3, deque.peekLast());
    }

    @Test
    void testDequeOfferFirstAndLast() {
        assertTrue(deque.offerFirst(-99));
        assertTrue(deque.offerLast(99));
    }

    @Test
    void testDequeRemoveFirstAndLast() {
        assertEquals(1, deque.removeFirst());
        assertEquals(3, deque.removeLast());
        assertEquals(2, deque.removeLast());
        assertThrows(NoSuchElementException.class, deque::removeFirst);
        assertThrows(NoSuchElementException.class, deque::removeLast);
    }

    @Test
    void testDequeRemoveLast() {
        assertEquals(3, deque.removeLast());
        assertEquals(2, deque.removeLast());
        assertEquals(1, deque.removeLast());
        assertThrows(NoSuchElementException.class, deque::pop);
    }
}
