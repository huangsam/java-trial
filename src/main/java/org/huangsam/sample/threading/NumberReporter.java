package org.huangsam.sample.threading;

public class NumberReporter {
    public void report(Integer result, Integer id) {
        System.out.println("Crunched " + result + " with thread " + id);
    }
}
