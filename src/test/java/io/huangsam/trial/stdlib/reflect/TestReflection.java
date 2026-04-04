package io.huangsam.trial.stdlib.reflect;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static io.huangsam.trial.stdlib.reflect.AnnotationExplorer.SimpleStuff;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Testing out some basic reflection concepts here. The pro of reflection is the flexibility
 * to create and modify classes at runtime. The cons of reflection are its heavy reliance on
 * strings as well as performance loss.
 *
 * @see <a href="https://www.youtube.com/watch?v=bhhMJSKNCQY">YouTube on Relection</a>
 * @see <a href="https://www.youtube.com/watch?v=vKVzRbsMnTQ">YouTube on Optionals</a>
 */
public class TestReflection {
    private static ReflectionExplorer explorer;
    private static AnnotationExplorer annotationExplorer;

    @BeforeEach
    void setupExplorer() {
        explorer = new ReflectionExplorer(new MysteryCar());
        annotationExplorer = new AnnotationExplorer();
    }

    @Test
    void testMiragesHaveSameSpecs() {
        assertTrue(explorer.car().hasSameSpecs(new MysteryCar(4, 0)));
    }

    @Test
    void testMiragesHaveDifferentSpecs() {
        assertFalse(explorer.car().hasSameSpecs(new MysteryCar(6, 0)));
        assertFalse(explorer.car().hasSameSpecs(new MysteryCar(4, 1)));
    }

    @Test
    void testGetCarFields() {
        Field[] fields = explorer.getCarFields();
        assertEquals(2, fields.length);

        Map<String, Field> fieldMap = Arrays.stream(fields)
                .collect(Collectors.toMap(Field::getName, field -> field));

        Field wheelsField = fieldMap.get("wheels");
        Field milesField = fieldMap.get("miles");

        assertNotNull(wheelsField);
        assertNotNull(milesField);

        // https://www.youtube.com/watch?v=DkZr7_c9ry8
        // Using the consolidated AnnotationExplorer
        assertTrue(annotationExplorer.hasFieldAnnotation(MysteryCar.class, "wheels", SimpleStuff.class));
        assertFalse(annotationExplorer.hasFieldAnnotation(MysteryCar.class, "miles", SimpleStuff.class));
    }

    @Test
    void testSetPrivateField() throws IllegalAccessException {
        int expectedWheels = 4;

        MysteryCar car = explorer.car();

        Optional<Field> optionalField = explorer.getCarField("wheels");
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
        MysteryCar car = explorer.car();

        Optional<Method> optionalMethod = explorer.getCarMethod("getMileInfo");
        Method method = optionalMethod.orElseThrow();

        method.setAccessible(true);
        assertTrue(method.canAccess(car));

        assertEquals("Traveled 0 miles", method.invoke(car));

        method.setAccessible(false);
        assertFalse(method.canAccess(car));

        assertThrows(IllegalAccessException.class, () -> method.invoke(car));
    }

    @Test
    void testEmptyField() {
        Optional<Field> optionalField = explorer.getCarField("foo");
        assertTrue(optionalField.isEmpty());
    }

    @Test
    void testEmptyMethod() {
        Optional<Method> optionalMethod = explorer.getCarMethod("foo");
        assertTrue(optionalMethod.isEmpty());
    }
}
