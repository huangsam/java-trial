package org.huangsam.sample.mirror;

public class MysteryCar {
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

    private String jump() {
        return "Jump after " + miles + " miles";
    }
}
