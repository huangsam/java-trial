package org.huangsam.sample.numerical;

import org.huangsam.sample.TrialRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NumberReporter {
    private static final Logger LOG = LoggerFactory.getLogger(TrialRunner.class);

    public void report(Integer result, Integer id) {
        LOG.info("Crunched {} with thread {}", result, id);
    }
}
