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

    public void drive() {
        System.out.println("Drive with " + wheels + " wheels");
    }

    private void jump() {
        System.out.println("Jump after " + miles + " miles");
    }

    public boolean hasSameSpecs(MysteryCar other) {
        return wheels == other.wheels && miles == other.miles;
    }
}
