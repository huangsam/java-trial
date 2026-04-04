package io.huangsam.trial.stdlib.reflect;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Explores classes, methods, and fields for custom annotations.
 * This class consolidates reflection-based discovery logic and includes
 * the definitions for the annotations it processes.
 */
public class AnnotationExplorer {
    /**
     * Constructs an annotation explorer.
     */
    public AnnotationExplorer() {
        // Default constructor
    }

    /**
     * A marker annotation for tagging simple fields or methods.
     */
    @Target({ElementType.FIELD, ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface SimpleStuff {
    }

    /**
     * A parameterized annotation for tagging classes or methods with metadata.
     */
    @Target({ElementType.TYPE, ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface TrialMetadata {
        /**
         * Name of the metadata entry.
         * @return the name
         */
        String value() default "DefaultName";

        /**
         * Version of the metadata entry.
         * @return the version
         */
        int version() default 1;
    }

    /**
     * Finds metadata on the class itself.
     *
     * @param clazz the class to inspect
     * @return an Optional of TrialMetadata if present
     */
    public Optional<TrialMetadata> getClassMetadata(Class<?> clazz) {
        return Optional.ofNullable(clazz.getAnnotation(TrialMetadata.class));
    }

    /**
     * Gets names of all methods annotated with a specific annotation class.
     *
     * @param clazz the class to inspect
     * @param annotationClass the annotation class to search for
     * @return a list of method names
     */
    public List<String> getAnnotatedMethodNames(Class<?> clazz, Class<? extends Annotation> annotationClass) {
        List<String> names = new ArrayList<>();
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(annotationClass)) {
                names.add(method.getName());
            }
        }
        return names;
    }

    /**
     * Gets names of all fields annotated with a specific annotation class.
     *
     * @param clazz the class to inspect
     * @param annotationClass the annotation class to search for
     * @return a list of field names
     */
    public List<String> getAnnotatedFieldNames(Class<?> clazz, Class<? extends Annotation> annotationClass) {
        List<String> names = new ArrayList<>();
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(annotationClass)) {
                names.add(field.getName());
            }
        }
        return names;
    }

    /**
     * Checks if a field on a class has a specific annotation.
     *
     * @param clazz the class containing the field
     * @param fieldName the name of the field
     * @param annotationClass the annotation to look for
     * @return true if the annotation is present, false otherwise
     */
    public boolean hasFieldAnnotation(Class<?> clazz, String fieldName, Class<? extends Annotation> annotationClass) {
        try {
            Field field = clazz.getDeclaredField(fieldName);
            return field.isAnnotationPresent(annotationClass);
        } catch (NoSuchFieldException e) {
            return false;
        }
    }
}
