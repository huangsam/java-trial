package io.huangsam.trial.stdlib.stream;

import java.util.List;
import java.util.stream.LongStream;

/**
 * Explores parallel processing with the Stream API.
 * Demonstrates the use of .parallel() and its performance considerations.
 */
public class ParallelStreamExplorer {

    /**
     * Computes the sum of range of numbers using a sequential stream.
     *
     * @param limit the upper bound of the range
     * @return the sum
     */
    public long sumSequential(long limit) {
        return LongStream.rangeClosed(1, limit)
                .reduce(0L, Long::sum);
    }

    /**
     * Computes the sum of range of numbers using a parallel stream.
     *
     * @param limit the upper bound of the range
     * @return the sum
     */
    public long sumParallel(long limit) {
        return LongStream.rangeClosed(1, limit)
                .parallel()
                .reduce(0L, Long::sum);
    }

    /**
     * Processes a list of strings in parallel to uppercase.
     *
     * @param inputs list of strings
     * @return list of uppercased strings
     */
    public List<String> processInParallel(List<String> inputs) {
        return inputs.parallelStream()
                .map(String::toUpperCase)
                .toList();
    }
}
