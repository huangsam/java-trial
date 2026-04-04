package io.huangsam.trial.concurrent.workload;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Reports the result of a number crunching operation.
 */
public class NumberReporter {
    private static final Logger LOG = LoggerFactory.getLogger(NumberReporter.class);

    /**
     * Constructs a number reporter.
     */
    public NumberReporter() {
        // Default constructor
    }

    /**
     * Reports the crunch result and original input.
     *
     * @param result the resulting value
     * @param input the input value
     */
    public void report(Long result, Long input) {
        LOG.info("Crunched {} with input {}", result, input);
    }
}
