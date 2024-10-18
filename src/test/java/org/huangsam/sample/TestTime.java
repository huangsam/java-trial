package org.huangsam.sample;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for the java.time library.
 *
 * @see <a href="https://www.baeldung.com/java-8-date-time-intro">Bealdung on time</a>
 */
public class TestTime {
    private static final LocalTime SIX_TIME = LocalTime.of(6, 0);

    private static final LocalDateTime SPECIAL_DATETIME = LocalDateTime.of(2000, Month.JANUARY, 1, 0, 0);

    private static final String UTC_ID = "UTC";

    @Test
    void testLocalTimeOffSet() {
        assertEquals(LocalTime.parse("06:00"), SIX_TIME);
        assertEquals(LocalTime.parse("07:00"), SIX_TIME.plusHours(1));
        assertEquals(LocalTime.parse("05:00"), SIX_TIME.minusHours(1));
    }

    @Test
    void testLocalTimePortions() {
        assertEquals(6, SIX_TIME.getHour());
        assertEquals(0, SIX_TIME.getMinute());
        assertTrue(SIX_TIME.isBefore(LocalTime.parse("06:01")));
    }

    @Test
    void testLocalTimeBoundaries() {
        assertEquals(0, LocalTime.MIN.getHour());
        assertEquals(0, LocalTime.MIN.getMinute());
        assertEquals(23, LocalTime.MAX.getHour());
        assertEquals(59, LocalTime.MAX.getMinute());
    }

    @Test
    void testLocalDateTimeOffset() {
        assertEquals(LocalDateTime.parse("2000-01-01T00:00:00"), SPECIAL_DATETIME);
        assertEquals(LocalDateTime.parse("2000-01-02T00:00:00"), SPECIAL_DATETIME.plusDays(1));
        assertEquals(LocalDateTime.parse("1999-12-31T00:00:00"), SPECIAL_DATETIME.minusDays(1));
    }

    @Test
    void testUtcZoneIsStandard() {
        assertTrue(ZoneId.getAvailableZoneIds().contains(UTC_ID));
        assertEquals(ZoneId.of(UTC_ID), ZoneId.ofOffset(UTC_ID, ZoneOffset.UTC));
    }

    @Test
    void testZonedTimeHasUtcZone() {
        ZoneId expectedZone = ZoneId.of(UTC_ID);
        ZonedDateTime dateTime = ZonedDateTime.of(SPECIAL_DATETIME, expectedZone);
        assertEquals(expectedZone, dateTime.getZone());
    }

    @Test
    void testPeriodsAndDates() {
        int expectedDays = 5;
        LocalDate initialDate = LocalDate.parse("2007-05-10");
        LocalDate finalDate = initialDate.plus(Period.ofDays(expectedDays));
        assertEquals(expectedDays, Period.between(initialDate, finalDate).getDays());
        assertEquals(expectedDays, ChronoUnit.DAYS.between(initialDate, finalDate));
    }
}
