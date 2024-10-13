package org.huangsam.sample.numerical;

public class NumberReporter {
    public void report(Integer result, Integer id) {
        System.out.println("Crunched " + result + " with thread " + id);
    }
}
