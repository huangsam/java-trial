package org.huangsam.sample;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.stream.Stream;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Using data structures like List, Map, Stack, Set are pretty common.
 * At the same time, it's good to touch a Deque as it serves both
 * the purpose of a stack and a queue. Getting the hang of its
 * methods can be useful for a large variety of scenarios.
 */
public class TestArrayDeque {
    private static final Deque<Integer> DEQUE = new ArrayDeque<>();

    @BeforeEach
    void resetDeque() {
        DEQUE.clear();
        Stream.of(1, 2, 3).forEach(DEQUE::add);
    }

    @Test
    void testDequeSize() {
        assertEquals(3, DEQUE.size());
    }

    @Test
    void testDequePeekFirstAndLast() {
        assertEquals(1, DEQUE.peekFirst());
        assertEquals(3, DEQUE.peekLast());
    }

    @Test
    void testDequeOfferThenPeek() {
        DEQUE.offerFirst(-99);
        DEQUE.offerLast(99);
        assertEquals(-99, DEQUE.peekFirst());
        assertEquals(99, DEQUE.peekLast());
    }

    @Test
    void testDequePop() {
        assertEquals(1, DEQUE.pop());
        assertEquals(2, DEQUE.pop());
        assertEquals(3, DEQUE.pop());
        assertThrows(NoSuchElementException.class, DEQUE::pop);
    }
}
