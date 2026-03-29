package io.huangsam.trial.features.modern;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestLanguageExplorer {

    private final LanguageExplorer explorer = new LanguageExplorer();

    @Test
    void testTextBlocks() {
        String json = explorer.showcaseTextBlocks();
        assertTrue(json.contains("\"name\": \"Java Playground\""));
        assertTrue(json.contains("\"version\": \"21\""));
        // Check for multiple lines
        assertTrue(json.lines().count() > 5);
    }

    @Test
    void testSealedClasses() {
        LanguageExplorer.Shape circle = new LanguageExplorer.Circle(2.0);
        LanguageExplorer.Shape square = new LanguageExplorer.Square(3.0);

        assertEquals(Math.PI * 4, circle.area(), 0.001);
        assertEquals(9.0, square.area(), 0.001);

        // Demonstrate pattern matching with sealed type (already stable in Java 21)
        String description = switch (circle) {
            case LanguageExplorer.Circle(double r) -> "Circle with radius " + r;
            case LanguageExplorer.Square(double s) -> "Square with side " + s;
        };
        assertEquals("Circle with radius 2.0", description);
    }

    @Test
    void testFormatting() {
        String result = explorer.showcaseFormatting("Trial", "21");
        assertEquals("Welcome to Trial (Running on Java 21)", result);
    }
}
