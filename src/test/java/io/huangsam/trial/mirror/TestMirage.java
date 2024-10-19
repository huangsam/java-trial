package io.huangsam.trial.mirror;

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
 * @see <a href="https://www.youtube.com/watch?v=bhhMJSKNCQY">YouTube on Relection</a>
 * @see <a href="https://www.youtube.com/watch?v=vKVzRbsMnTQ">YouTube on Optionals</a>
 */
public class TestMirage {
    private static Mirage mirage;

    @BeforeEach
    void setupMirage() {
        mirage = new Mirage(new MysteryCar());
    }

    @Test
    void testMiragesHaveSameSpecs() {
        assertTrue(mirage.car().hasSameSpecs(new MysteryCar(4, 0)));
    }

    @Test
    void testMiragesHaveDifferentSpecs() {
        assertFalse(mirage.car().hasSameSpecs(new MysteryCar(6, 0)));
        assertFalse(mirage.car().hasSameSpecs(new MysteryCar(4, 1)));
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
        Field field = optionalField.orElseThrow();

        field.setAccessible(true);
        assertTrue(field.canAccess(car));

        field.set(car, expectedWheels);
        assertEquals(expectedWheels, (Integer) field.get(car));

        field.setAccessible(false);
        assertFalse(field.canAccess(car));

        assertThrows(IllegalAccessException.class, () -> field.get(car));
    }

    @Test
    void testInvokePrivateMethod() throws IllegalAccessException, InvocationTargetException {
        MysteryCar car = mirage.car();

        Optional<Method> optionalMethod = mirage.getCarMethod("getMileInfo");
        Method method = optionalMethod.orElseThrow();

        method.setAccessible(true);
        assertTrue(method.canAccess(car));

        assertEquals("Traveled 0 miles", (String) method.invoke(car));

        method.setAccessible(false);
        assertFalse(method.canAccess(car));

        assertThrows(IllegalAccessException.class, () -> method.invoke(car));
    }

    @Test
    void testEmptyField() {
        Optional<Field> optionalField = mirage.getCarField("foo");
        assertTrue(optionalField.isEmpty());
    }

    @Test
    void testEmptyMethod() {
        Optional<Method> optionalMethod = mirage.getCarMethod("foo");
        assertTrue(optionalMethod.isEmpty());
    }
}
