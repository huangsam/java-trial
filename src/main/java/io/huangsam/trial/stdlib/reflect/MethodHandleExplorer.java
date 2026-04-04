package io.huangsam.trial.stdlib.reflect;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * Demonstrates high-performance reflection using MethodHandles.
 * MethodHandles are more efficient than traditional reflection and are used
 * internally by the JVM for features like Lambda expressions.
 */
public class MethodHandleExplorer {

    private final MethodHandles.Lookup lookup = MethodHandles.lookup();

    /**
     * Constructs a method handle explorer.
     */
    public MethodHandleExplorer() {
        // Default constructor
    }

    /**
     * Finds a MethodHandle for a virtual (instance) method.
     *
     * @param receiverClass the class containing the method
     * @param methodName    the name of the method
     * @param returnType    the return type of the method
     * @param parameterTypes the parameter types of the method
     * @return the MethodHandle
     * @throws NoSuchMethodException  if the method does not exist
     * @throws IllegalAccessException if the method is not accessible
     */
    public MethodHandle findVirtual(Class<?> receiverClass, String methodName, Class<?> returnType, Class<?>... parameterTypes)
            throws NoSuchMethodException, IllegalAccessException {
        MethodType type = MethodType.methodType(returnType, parameterTypes);
        return lookup.findVirtual(receiverClass, methodName, type);
    }

    /**
     * Finds a MethodHandle for a private method using a private lookup.
     *
     * @param receiverClass the class containing the method
     * @param methodName    the name of the method
     * @param returnType    the return type of the method
     * @return the MethodHandle
     * @throws NoSuchMethodException  if the method does not exist
     * @throws IllegalAccessException if the method is not accessible
     */
    public MethodHandle findPrivateVirtual(Class<?> receiverClass, String methodName, Class<?> returnType)
            throws NoSuchMethodException, IllegalAccessException {
        MethodHandles.Lookup privateLookup = MethodHandles.privateLookupIn(receiverClass, lookup);
        MethodType type = MethodType.methodType(returnType);
        return privateLookup.findVirtual(receiverClass, methodName, type);
    }

    /**
     * Finds a MethodHandle for a field getter.
     *
     * @param receiverClass the class containing the field
     * @param fieldName     the name of the field
     * @param fieldType     the type of the field
     * @return the MethodHandle for the getter
     * @throws NoSuchFieldException   if the field does not exist
     * @throws IllegalAccessException if the field is not accessible
     */
    /**
     * Finds a MethodHandle for a private field getter.
     *
     * @param receiverClass the class containing the field
     * @param fieldName     the name of the field
     * @param fieldType     the type of the field
     * @return the MethodHandle for the getter
     * @throws NoSuchFieldException   if the field does not exist
     * @throws IllegalAccessException if the field is not accessible
     */
    public MethodHandle findPrivateGetter(Class<?> receiverClass, String fieldName, Class<?> fieldType)
            throws NoSuchFieldException, IllegalAccessException {
        MethodHandles.Lookup privateLookup = MethodHandles.privateLookupIn(receiverClass, lookup);
        return privateLookup.findGetter(receiverClass, fieldName, fieldType);
    }
}
