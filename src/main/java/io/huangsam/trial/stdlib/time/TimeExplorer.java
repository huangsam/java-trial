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

public class TimeExplorer {
    public LocalDateTime parseIsoDateTime(String dateTimeString) {
        return LocalDateTime.parse(dateTimeString);
    }

    public long hoursBetween(LocalTime start, LocalTime end) {
        return ChronoUnit.HOURS.between(start, end);
    }

    public long secondsBetween(LocalTime start, LocalTime end) {
        return Duration.between(start, end).getSeconds();
    }

    public int daysBetween(LocalDate start, LocalDate end) {
        return Period.between(start, end).getDays();
    }

    public String formatIsoDate(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ISO_DATE);
    }

    public ZoneId getUtcZone() {
        return ZoneId.of("UTC");
    }

    public ZonedDateTime toZonedDateTime(LocalDateTime dateTime, ZoneId zoneId) {
        return ZonedDateTime.of(dateTime, zoneId);
    }
}
