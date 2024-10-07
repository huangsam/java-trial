package org.huangsam.sample;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayDeque;
import java.util.stream.Stream;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestArrayDeque {
    private static final ArrayDeque<Integer> deque = new ArrayDeque<>();

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
