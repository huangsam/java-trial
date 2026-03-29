package io.huangsam.trial.model.human;

/**
 * Represents a user profile in the system.
 *
 * @param name the name of the user
 * @param email the email of the user
 * @param age the age of the user
 */
public record UserProfile(String name, String email, int age) {
}
