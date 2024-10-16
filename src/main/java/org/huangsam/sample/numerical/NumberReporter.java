package org.huangsam.sample.numerical;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NumberReporter {
    private static final Logger LOG = LoggerFactory.getLogger(NumberReporter.class);

    public void report(Long result, Integer id) {
        LOG.info("Crunched {} with thread {}", result, id);
    }
}
