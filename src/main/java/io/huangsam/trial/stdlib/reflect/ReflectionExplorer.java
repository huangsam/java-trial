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
    /**
     * Gets all declared fields of the car.
     *
     * @return an array of fields
     */
    public Field[] getCarFields() {
        return car.getClass().getDeclaredFields();
    }

    /**
     * Gets a specific declared field of the car by name.
     *
     * @param fieldName the name of the field
     * @return an Optional containing the field if found, or empty otherwise
     */
    public Optional<Field> getCarField(String fieldName) {
        try {
            return Optional.of(car.getClass().getDeclaredField(fieldName));
        } catch (NoSuchFieldException e) {
            return Optional.empty();
        }
    }

    /**
     * Gets a specific declared method of the car by name.
     *
     * @param methodName the name of the method
     * @return an Optional containing the method if found, or empty otherwise
     */
    public Optional<Method> getCarMethod(String methodName) {
        try {
            return Optional.of(car.getClass().getDeclaredMethod(methodName));
        } catch (NoSuchMethodException e) {
            return Optional.empty();
        }
    }
}
