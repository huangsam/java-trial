package org.huangsam.sample.mirror;

import javax.annotation.Nullable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public record Mirage(MysteryCar car) {
    public Field[] getCarFields() {
        return car.getClass().getDeclaredFields();
    }

    @Nullable
    public Field getCarField(String fieldName) {
        try {
            return car.getClass().getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            return null;
        }
    }

    @Nullable
    public Method getCarMethod(String methodName) {
        try {
            return car.getClass().getDeclaredMethod(methodName);
        } catch (NoSuchMethodException e) {
            return null;
        }
    }
}
