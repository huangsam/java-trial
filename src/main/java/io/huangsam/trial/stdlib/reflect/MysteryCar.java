package io.huangsam.trial.stdlib.reflect;

/**
 * A mystery car class used for testing reflection and annotations.
 */
public class MysteryCar {
    @AnnotationExplorer.SimpleStuff
    private final int wheels;

    private final int miles;

    /**
     * Constructs a mystery car with default values.
     */
    public MysteryCar() {
        wheels = 4;
        miles = 0;
    }

    /**
     * Constructs a mystery car with explicit values.
     *
     * @param wheels Number of wheels
     * @param miles Number of miles
     */
    public MysteryCar(int wheels, int miles) {
        this.wheels = wheels;
        this.miles = miles;
    }

    /**
     * Checks if this car has the same specs as another car.
     *
     * @param other the other car to compare with
     * @return true if specs match
     */
    public boolean hasSameSpecs(MysteryCar other) {
        return wheels == other.wheels && getMileInfo().equals(other.getMileInfo());
    }

    // Left as private to test the power of reflection!
    private String getMileInfo() {
        return "Traveled " + miles + " miles";
    }
}
