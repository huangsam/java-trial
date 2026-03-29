package io.huangsam.trial.features.modern;

/**
 * Demonstrates standard modern Java syntax features (Java 15, 17, 21).
 */
public class ModernSyntaxExplorer {

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
        double area();
    }

    public static final record Circle(double radius) implements Shape {
        @Override
        public double area() {
            return Math.PI * radius * radius;
        }
    }

    public static final record Square(double side) implements Shape {
        @Override
        public double area() {
            return side * side;
        }
    }

    /**
     * Demonstrates modern String formatting (Standard since Java 15).
     *
     * @param name the name to format
     * @param version the version to format
     * @return a formatted string
     */
    public String showcaseFormatting(String name, String version) {
        return "Welcome to %s (Running on Java %s)".formatted(name, version);
    }
}
