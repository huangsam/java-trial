package io.huangsam.trial.stdlib.error;

/**
 * Exception thrown when a requested resource is not found.
 * Demonstrates custom checked exception handling.
 */
public class ResourceNotFoundException extends Exception {
    /**
     * Constructs a new exception with the specified detail message.
     *
     * @param message the detail message
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a new exception with the specified detail message and cause.
     *
     * @param message the detail message
     * @param cause the cause
     */
    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
