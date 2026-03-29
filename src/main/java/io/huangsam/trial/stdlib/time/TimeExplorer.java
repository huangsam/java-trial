package io.huangsam.trial.stdlib.time;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Demonstrates the use of the standard java.time package for time-related
 * operations.
 */
public class TimeExplorer {
    /**
     * Parses an ISO-8601 date-time string into a LocalDateTime.
     *
     * @param dateTimeString the date-time string to parse
     * @return the parsed LocalDateTime
     */
    public LocalDateTime parseIsoDateTime(String dateTimeString) {
        return LocalDateTime.parse(dateTimeString);
    }

    /**
     * Calculates the number of hours between two LocalTimes.
     *
     * @param start the start time
     * @param end   the end time
     * @return the number of hours between start and end
     */
    public long hoursBetween(LocalTime start, LocalTime end) {
        return ChronoUnit.HOURS.between(start, end);
    }

    /**
     * Calculates the number of seconds between two LocalTimes.
     *
     * @param start the start time
     * @param end   the end time
     * @return the number of seconds between start and end
     */
    public long secondsBetween(LocalTime start, LocalTime end) {
        return Duration.between(start, end).getSeconds();
    }

    /**
     * Calculates the number of days between two LocalDate objects.
     *
     * @param start the start date
     * @param end   the end date
     * @return the number of days between start and end
     */
    public int daysBetween(LocalDate start, LocalDate end) {
        return Period.between(start, end).getDays();
    }

    /**
     * Formats a LocalDateTime into an ISO-8601 date string.
     *
     * @param dateTime the LocalDateTime to format
     * @return the formatted date string
     */
    public String formatIsoDate(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ISO_DATE);
    }

    /**
     * Gets the UTC time zone.
     *
     * @return the UTC time zone
     */
    public ZoneId getUtcZone() {
        return ZoneId.of("UTC");
    }

    /**
     * Converts a LocalDateTime to a ZonedDateTime with the given time zone.
     *
     * @param dateTime the LocalDateTime to convert
     * @param zoneId   the time zone to convert to
     * @return the converted ZonedDateTime
     */
    public ZonedDateTime toZonedDateTime(LocalDateTime dateTime, ZoneId zoneId) {
        return ZonedDateTime.of(dateTime, zoneId);
    }
}
