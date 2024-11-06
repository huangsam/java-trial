package io.huangsam.trial.etl;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestFlakyMath {
    @ParameterizedTest
    @ValueSource(longs = {1L, 2L, 3L})
    void testOptionalChainingOnLittleNumber(long smallish) {
        assertTrue(getOptionalChain(smallish).isEmpty());
    }

    @ParameterizedTest
    @ValueSource(longs = {5L, 8L, 13L})
    void testOptionalChainingOnBigNumber(long biggish) {
        assertTrue(getOptionalChain(biggish).isPresent());
    }

    private Optional<Long> getOptionalChain(Long value) {
        return Optional.of(value)
                .flatMap(l -> (l < 3) ? Optional.empty() : Optional.of(l * 3L))
                .map(l -> l * 2L)
                .filter(l -> l > 20);
    }
}
