package io.huangsam.trial.guava;

import static com.google.common.base.Preconditions.checkArgument;

public class SimpleValidator {
    private SimpleValidator() {
    }

    public static int fibonacci(int n) {
        checkArgument(n >= 0, "Got an invalid input %d", n);
        if (n <= 2) {
            return n;
        }
        return fibonacci(n - 1) - fibonacci(n - 2);
    }
}
