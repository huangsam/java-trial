package io.huangsam.trial.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Tests for ImmutableTrial.
 */
public class TestImmutableTrial {

    @Test
    public void testDefensiveCopyInConstructor() {
        List<String> tags = new ArrayList<>();
        tags.add("tag1");
        Date date = new Date();
        ImmutableTrial trial = new ImmutableTrial("Title", tags, date);

        tags.add("tag2"); // Change original list
        date.setTime(0); // Change original date

        assertEquals(1, trial.getTags().size(), "Trial tags should not have changed");
        assertNotSame(0L, trial.getCreatedAt().getTime(), "Trial date should not have changed");
    }

    @Test
    public void testGetTitle() {
        ImmutableTrial trial = new ImmutableTrial("My Title", List.of(), new Date());
        assertEquals("My Title", trial.getTitle(), "Title should match the one passed in constructor");
    }

    @Test
    public void testDefensiveCopyInGetter() {
        ImmutableTrial trial = new ImmutableTrial("Title", List.of("tag1"), new Date());

        Date dateFromGetter = trial.getCreatedAt();
        dateFromGetter.setTime(0); // Try to modify internal state via returned date

        assertNotSame(0L, trial.getCreatedAt().getTime(), "Trial date should not have changed via getter modification");
    }

    @Test
    public void testUnmodifiableListFromGetter() {
        ImmutableTrial trial = new ImmutableTrial("Title", List.of("tag1"), new Date());
        List<String> tags = trial.getTags();

        assertThrows(UnsupportedOperationException.class, () -> tags.add("newTag"),
                "Modifying the list returned by getTags() should throw an exception");
    }
}
