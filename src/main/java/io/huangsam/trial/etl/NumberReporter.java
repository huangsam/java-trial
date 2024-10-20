package io.huangsam.trial.etl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NumberReporter {
    private static final Logger LOG = LoggerFactory.getLogger(NumberReporter.class);

    public void report(Long result, Long input) {
        LOG.info("Crunched {} with input {}", result, input);
    }
}
