package io.huangsam.trial.features.modern;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ModernSyntaxExplorerTest {

    private final ModernSyntaxExplorer explorer = new ModernSyntaxExplorer();

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
        ModernSyntaxExplorer.Shape circle = new ModernSyntaxExplorer.Circle(2.0);
        ModernSyntaxExplorer.Shape square = new ModernSyntaxExplorer.Square(3.0);

        assertEquals(Math.PI * 4, circle.area(), 0.001);
        assertEquals(9.0, square.area(), 0.001);

        // Demonstrate pattern matching with sealed type (already stable in Java 21)
        String description = switch (circle) {
            case ModernSyntaxExplorer.Circle(double r) -> "Circle with radius " + r;
            case ModernSyntaxExplorer.Square(double s) -> "Square with side " + s;
        };
        assertEquals("Circle with radius 2.0", description);
    }

    @Test
    void testFormatting() {
        String result = explorer.showcaseFormatting("Trial", "21");
        assertEquals("Welcome to Trial (Running on Java 21)", result);
    }
}
