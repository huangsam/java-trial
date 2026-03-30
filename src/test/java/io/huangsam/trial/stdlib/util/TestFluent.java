package io.huangsam.trial.stdlib.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestFluent {

    @Test
    void testFluentBuilder() {
        // Since StudentBuilder correctly typed itself as StudentBuilder instead of PersonBuilder,
        // we can chain the inherited 'withName()' straight into the specific 'withMajor()'
        // and return the target Student record directly.
        FluentExplorer.Student student = new FluentExplorer.StudentBuilder()
                .withName("Alice")
                .withAge(22)
                .withMajor("Computer Science")
                .build();

        assertEquals("Alice", student.name());
        assertEquals(22, student.age());
        assertEquals("Computer Science", student.major());
    }
}
