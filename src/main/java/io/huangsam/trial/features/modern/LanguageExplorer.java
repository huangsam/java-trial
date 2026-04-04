package io.huangsam.trial.features.modern;

/**
 * Demonstrates standard modern Java syntax features (Java 15, 17, 21).
 */
public class LanguageExplorer {
    /**
     * Constructs a language explorer.
     */
    public LanguageExplorer() {
        // Default constructor
    }

    /**
     * Demonstrates Text Blocks (Standard since Java 15).
     *
     * @return a multi-line JSON string
     */
    public String showcaseTextBlocks() {
        return """
                {
                  "name": "Java Playground",
                  "version": "21",
                  "features": [
                    "Text Blocks",
                    "Sealed Classes",
                    "Records",
                    "Pattern Matching"
                  ]
                }
                """;
    }

    /**
     * Demonstrates Sealed Classes (Standard since Java 17).
     */
    public sealed interface Shape permits Circle, Square {
        /**
         * Calculates the area of the shape.
         *
         * @return the area
         */
        double area();
    }

    /**
     * A circle implementation of Shape.
     *
     * @param radius the radius of the circle
     */
    public record Circle(double radius) implements Shape {
        @Override
        public double area() {
            return Math.PI * radius * radius;
        }
    }

    /**
     * A square implementation of Shape.
     *
     * @param side the side length of the square
     */
    public record Square(double side) implements Shape {
        @Override
        public double area() {
            return side * side;
        }
    }

    /**
     * Demonstrates modern String formatting (Standard since Java 15).
     *
     * @param name    the name to format
     * @param version the version to format
     * @return a formatted string
     */
    public String showcaseFormatting(String name, String version) {
        return "Welcome to %s (Running on Java %s)".formatted(name, version);
    }
}
