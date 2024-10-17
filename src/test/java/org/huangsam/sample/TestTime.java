package org.huangsam.sample;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestTime {
    private static final LocalTime SIX = LocalTime.of(6, 0);

    @Test
    void testLocalTimeOffSet() {
        assertEquals(LocalTime.parse("06:00"), SIX);
        assertEquals(LocalTime.parse("07:00"), SIX.plusHours(1));
        assertEquals(LocalTime.parse("05:00"), SIX.minusHours(1));
    }

    @Test
    void testLocalTimePortions() {
        assertEquals(6, SIX.getHour());
        assertEquals(0, SIX.getMinute());
        assertTrue(SIX.isBefore(LocalTime.parse("06:01")));
    }

    @Test
    void testLocalTimeBoundaries() {
        assertEquals(0, LocalTime.MIN.getHour());
        assertEquals(0, LocalTime.MIN.getMinute());
        assertEquals(23, LocalTime.MAX.getHour());
        assertEquals(59, LocalTime.MAX.getMinute());
    }
}
