package org.huangsam.sample;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayDeque;
import java.util.stream.Stream;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestArrayDeque {
    private static final ArrayDeque<Integer> DEQUE = new ArrayDeque<>();

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
