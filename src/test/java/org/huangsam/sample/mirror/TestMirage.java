package org.huangsam.sample.mirror;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Testing out some basic reflection concepts. Obviously, this can get more
 * involved and complex - just look at how the Spring framework has evolved!
 * The pros of reflection is the flexibility to create and modify classes
 * at runtime. The cons of reflection is its heavy reliance on strings, as
 * noted in these tests.
 *
 * @see <a href="https://www.youtube.com/watch?v=bhhMJSKNCQY">YouTube video</a>
 */
public class TestMirage {
    private static Mirage mirage;

    @BeforeEach
    void setupMirage() {
        mirage = new Mirage(new MysteryCar());
    }

    @Test
    void testGetCarFields() {
        Field[] fields = mirage.getCarFields();
        assertEquals(2, fields.length);

        Set<String> fieldNames = Arrays.stream(fields)
                .map(Field::getName)
                .collect(Collectors.toSet());

        assertTrue(fieldNames.contains("wheels"));
        assertTrue(fieldNames.contains("miles"));
    }

    @Test
    void testSetPrivateField() throws IllegalAccessException {
        int expectedWheels = 4;

        MysteryCar car = mirage.car();

        Optional<Field> optionalField = mirage.getCarField("wheels");
        assertTrue(optionalField.isPresent());
        Field field = optionalField.get();

        field.setAccessible(true);
        assertTrue(field.canAccess(car));

        field.set(car, expectedWheels);
        Integer wheels = (Integer) field.get(car);
        assertEquals(expectedWheels, wheels);

        field.setAccessible(false);
        assertFalse(field.canAccess(car));

        assertThrows(IllegalAccessException.class, () -> field.get(car));
    }

    @Test
    void testInvokePrivateMethod() throws IllegalAccessException, InvocationTargetException {
        MysteryCar car = mirage.car();

        Optional<Method> optionalMethod = mirage.getCarMethod("jump");
        assertTrue(optionalMethod.isPresent());
        Method method = optionalMethod.get();

        method.setAccessible(true);
        assertTrue(method.canAccess(car));

        method.invoke(car);

        method.setAccessible(false);
        assertFalse(method.canAccess(car));

        assertThrows(IllegalAccessException.class, () -> method.invoke(car));
    }

    @Test
    void testEmptyField() {
        Optional<Field> result = mirage.getCarField("foo");
        assertTrue(result.isEmpty());
    }

    @Test
    void testEmptyMethod() {
        Optional<Method> result = mirage.getCarMethod("foo");
        assertTrue(result.isEmpty());
    }
}
