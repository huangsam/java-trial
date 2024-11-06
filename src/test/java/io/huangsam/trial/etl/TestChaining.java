package io.huangsam.trial.etl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestChaining {
    private static final int SUM_TO_NINE = IntStream.of(1, 2, 3, 4, 5, 6, 7, 8, 9).sum();

    @ParameterizedTest
    @ValueSource(longs = {1L, 2L, 3L, 5L})
    void testOptionalChainingOnLittleNumber(long smallish) {
        Optional<Long> optional = getOptionalChain(smallish);
        assertTrue(optional.isEmpty());
        assertThrows(NoSuchElementException.class, optional::get);
    }

    @ParameterizedTest
    @ValueSource(longs = {8L, 13L, 21L, 34L})
    void testOptionalChainingOnBigNumber(long biggish) {
        Optional<Long> optional = getOptionalChain(biggish);
        assertTrue(optional.isPresent());
        assertEquals(biggish * 6L, optional.get());
    }

    @Test
    void testStreamChainingOnNestedIntegerList() {
        int listTotal = Stream.of(List.of(1, 2, 3), List.of(4, 5, 6), List.of(7, 8, 9))
                .flatMap(Collection::stream)
                .mapToInt(Integer::intValue)
                .sum();

        assertEquals(SUM_TO_NINE, listTotal);
    }

    @Test
    void testStreamChainingOnNestedIntegerArray() {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};

        int matrixTotal = Arrays.stream(matrix).flatMapToInt(Arrays::stream).sum();

        assertEquals(SUM_TO_NINE, matrixTotal);
    }

    private Optional<Long> getOptionalChain(Long value) {
        return Optional.of(value)
                .flatMap(l -> (l < 3) ? Optional.empty() : Optional.of(l * 3L))
                .map(l -> l * 2L)
                .filter(l -> l > 30);
    }
}
