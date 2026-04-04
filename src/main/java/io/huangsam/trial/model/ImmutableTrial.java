package io.huangsam.trial.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Demonstrates an immutable class with defensive copying.
 * Even though the fields are final, the objects they point to (List and Date) are mutable.
 * Defensive copying in both the constructor and getters ensures true immutability.
 */
public final class ImmutableTrial {

    private final String title;
    private final List<String> tags;
    private final Date createdAt;

    /**
     * Constructs an immutable trial instance.
     *
     * @param title the title of the trial
     * @param tags a list of tags (defensively copied)
     * @param createdAt the creation date (defensively copied)
     */
    public ImmutableTrial(String title, List<String> tags, Date createdAt) {
        this.title = title;
        // Defensive copy: even if the caller modifies the original list, this instance is safe.
        this.tags = tags != null ? new ArrayList<>(tags) : new ArrayList<>();
        // Defensive copy: Date is mutable!
        this.createdAt = createdAt != null ? new Date(createdAt.getTime()) : null;
    }

    /**
     * Gets the title.
     *
     * @return the title string
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the tags.
     *
     * @return an unmodifiable view of the tags list
     */
    public List<String> getTags() {
        // Return an unmodifiable view to prevent external modification.
        return Collections.unmodifiableList(tags);
    }

    /**
     * Gets the creation date.
     *
     * @return a defensive copy of the creation date
     */
    public Date getCreatedAt() {
        // Defensive copy on return: prevents the caller from using setTime() on the internal date.
        return createdAt != null ? new Date(createdAt.getTime()) : null;
    }
}
