package io.huangsam.trial.stdlib.error;

/**
 * Exception thrown when a requested resource is not found.
 * Demonstrates custom checked exception handling.
 */
public class ResourceNotFoundException extends Exception {
    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
