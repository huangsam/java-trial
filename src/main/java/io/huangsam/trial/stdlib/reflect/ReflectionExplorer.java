package io.huangsam.trial.stdlib.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Optional;

/**
 * Demonstrates the use of reflection to explore class fields and methods.
 *
 * @param car the car to explore
 */
public record ReflectionExplorer(MysteryCar car) {
    public Field[] getCarFields() {
        return car.getClass().getDeclaredFields();
    }

    public Optional<Field> getCarField(String fieldName) {
        try {
            return Optional.of(car.getClass().getDeclaredField(fieldName));
        } catch (NoSuchFieldException e) {
            return Optional.empty();
        }
    }

    public Optional<Method> getCarMethod(String methodName) {
        try {
            return Optional.of(car.getClass().getDeclaredMethod(methodName));
        } catch (NoSuchMethodException e) {
            return Optional.empty();
        }
    }
}
