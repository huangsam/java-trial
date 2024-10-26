package io.huangsam.trial.collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestList {
    private final List<Integer> list = new ArrayList<>();

    @BeforeEach
    void resetList() {
        list.clear();
    }

    @Test
    void addAndVerifyValues() {
        list.add(1);
        list.add(2);
        assertEquals(2, list.size());
    }
}
