package io.huangsam.trial.etl;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestFlakyMath {
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

    private Optional<Long> getOptionalChain(Long value) {
        return Optional.of(value)
                .flatMap(l -> (l < 3) ? Optional.empty() : Optional.of(l * 3L))
                .map(l -> l * 2L)
                .filter(l -> l > 30);
    }
}
