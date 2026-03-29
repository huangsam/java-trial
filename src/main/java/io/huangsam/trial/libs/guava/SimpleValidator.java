package io.huangsam.trial.libs.guava;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

public class SimpleValidator {
    private static final Logger LOG = LoggerFactory.getLogger(SimpleValidator.class);

    /**
     * Calculates the nth Fibonacci number.
     *
     * @param n the position in the Fibonacci sequence
     * @return the Fibonacci number at position n
     */
    public static int fibonacci(int n) {
        LOG.debug("Validating fibonacci input: n={}", n);
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

    /**
     * Returns a greeting string for the given name.
     *
     * @param name the name to salute
     * @return a greeting string
     */
    public static String salute(String name) {
        LOG.debug("Validating salute input: name={}", name);
        checkNotNull(name, "The string is null");
        return "Hello " + name;
    }
}
