package io.huangsam.trial;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Using data structures like List, Map, Stack, Set are pretty common.
 * At the same time, it's good to touch a Deque as it serves both
 * the purpose of a stack and a queue. Getting the hang of its
 * methods can be useful for a large variety of scenarios.
 */
public class TestDeque {
    private final Deque<Integer> deque = new ArrayDeque<>();

    @BeforeEach
    void resetDeque() {
        deque.clear();
        Stream.of(1, 2, 3).forEach(deque::add);
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
    void testDequeOfferThenPeek() {
        deque.offerFirst(-99);
        deque.offerLast(99);
        assertEquals(-99, deque.peekFirst());
        assertEquals(99, deque.peekLast());
    }

    @Test
    void testDequePop() {
        assertEquals(1, deque.pop());
        assertEquals(2, deque.pop());
        assertEquals(3, deque.pop());
        assertThrows(NoSuchElementException.class, deque::pop);
    }
}
