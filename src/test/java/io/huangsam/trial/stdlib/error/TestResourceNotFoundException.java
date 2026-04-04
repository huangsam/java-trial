package io.huangsam.trial.stdlib.error;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

public class TestResourceNotFoundException {

    @Test
    void testMessageConstructor() {
        String message = "Resource not found";
        ResourceNotFoundException ex = new ResourceNotFoundException(message);
        assertEquals(message, ex.getMessage());
    }

    @Test
    void testMessageAndCauseConstructor() {
        String message = "Resource missing";
        Throwable cause = new RuntimeException("Underlying cause");
        ResourceNotFoundException ex = new ResourceNotFoundException(message, cause);
        
        assertEquals(message, ex.getMessage());
        assertSame(cause, ex.getCause());
    }
}
