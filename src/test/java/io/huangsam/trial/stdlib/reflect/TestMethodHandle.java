package io.huangsam.trial.stdlib.reflect;

import org.junit.jupiter.api.Test;

import java.lang.invoke.MethodHandle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestMethodHandle {

    private final MethodHandleExplorer explorer = new MethodHandleExplorer();

    @Test
    void testFindVirtualAndInvoke() throws Throwable {
        MysteryCar car1 = new MysteryCar(4, 100);
        MysteryCar car2 = new MysteryCar(4, 100);

        // findVirtual for public hasSameSpecs(MysteryCar other)
        MethodHandle hasSameSpecs = explorer.findVirtual(MysteryCar.class, "hasSameSpecs", boolean.class, MysteryCar.class);
        assertNotNull(hasSameSpecs);

        // invokeExact requires the exact signature (receiver, then parameters)
        boolean result = (boolean) hasSameSpecs.invokeExact(car1, car2);
        assertTrue(result);
    }

    @Test
    void testFindPrivateVirtualAndInvoke() throws Throwable {
        MysteryCar car = new MysteryCar(4, 500);

        // findPrivateVirtual for the private getMileInfo()
        MethodHandle getMileInfo = explorer.findPrivateVirtual(MysteryCar.class, "getMileInfo", String.class);
        assertNotNull(getMileInfo);

        // invoke returns Object and performs type conversion
        String result = (String) getMileInfo.invoke(car);
        assertEquals("Traveled 500 miles", result);
    }

    @Test
    void testFindGetterAndInvoke() throws Throwable {
        MysteryCar car = new MysteryCar(6, 1200);

        // findPrivateGetter for private wheels
        MethodHandle getWheels = explorer.findPrivateGetter(MysteryCar.class, "wheels", int.class);
        assertNotNull(getWheels);

        // invoke returns object and perform unboxing
        int wheels = (int) getWheels.invoke(car);
        assertEquals(6, wheels);

        MethodHandle getMiles = explorer.findPrivateGetter(MysteryCar.class, "miles", int.class);
        assertEquals(1200, (int) getMiles.invokeExact(car));
    }
}
