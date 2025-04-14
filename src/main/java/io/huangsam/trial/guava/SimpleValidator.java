package io.huangsam.trial.guava;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

public class SimpleValidator {
    public static int fibonacci(int n) {
        checkArgument(n >= 0 && n <= 6, "Got invalid input %d", n);
        if (n <= 2) {
            return n;
        }
        int previous = 1, current = 2;
        for (int i = 2; i < n; i++) {
            int sum = previous + current;
            previous = current;
            current = sum;
        }
        return current;
    }

    public static String salute(String name) {
        checkNotNull(name, "The string is null");
        return "Hello " + name;
    }
}
