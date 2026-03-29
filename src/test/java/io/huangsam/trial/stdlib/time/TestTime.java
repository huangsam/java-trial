package io.huangsam.trial.stdlib.time;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for the {@code java.time} library.
 *
 * @see <a href="https://www.baeldung.com/java-8-date-time-intro">Bealdung on time</a>
 */
public class TestTime {
    private final TimeExplorer explorer = new TimeExplorer();

    private static final LocalTime SIX_TIME = LocalTime.of(6, 0);

    private static final LocalDateTime MILLENNIAL_DT = LocalDateTime.of(2000, 1, 1, 0, 0);

    @Test
    void testLocalTimeOffset() {
        assertEquals(LocalTime.parse("06:00"), SIX_TIME);
        assertEquals(7, explorer.hoursBetween(SIX_TIME.minusHours(6), SIX_TIME.plusHours(1)));
    }

    @Test
    void testLocalDateTimeParsing() {
        assertEquals(MILLENNIAL_DT, explorer.parseIsoDateTime("2000-01-01T00:00:00"));
    }

    @Test
    void testUtcNowZone() {
        ZoneId utc = explorer.getUtcZone();
        assertEquals("UTC", utc.getId());
    }

    @Test
    void testZonedTime() {
        ZoneId utc = explorer.getUtcZone();
        ZonedDateTime dateTime = explorer.toZonedDateTime(MILLENNIAL_DT, utc);
        assertEquals(utc, dateTime.getZone());
    }

    @Test
    void testDaysBetween() {
        LocalDate start = LocalDate.parse("2024-01-01");
        LocalDate end = LocalDate.parse("2024-01-06");
        assertEquals(5, explorer.daysBetween(start, end));
    }

    @Test
    void testSecondsBetween() {
        LocalTime start = LocalTime.of(10, 0, 0);
        LocalTime end = LocalTime.of(10, 0, 30);
        assertEquals(30, explorer.secondsBetween(start, end));
    }

    @Test
    void testFormatting() {
        assertEquals("2000-01-01", explorer.formatIsoDate(MILLENNIAL_DT));
    }
}
