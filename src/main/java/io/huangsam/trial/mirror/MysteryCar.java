package io.huangsam.trial.mirror;

public class MysteryCar {
    @SimpleStuff
    private final int wheels;

    private final int miles;

    public MysteryCar() {
        wheels = 4;
        miles = 0;
    }

    public MysteryCar(int wheels, int miles) {
        this.wheels = wheels;
        this.miles = miles;
    }

    public boolean hasSameSpecs(MysteryCar other) {
        return wheels == other.wheels && miles == other.miles;
    }

    // Left as private to test the power of reflection!
    private String getMileInfo() {
        return "Traveled " + miles + " miles";
    }
}
