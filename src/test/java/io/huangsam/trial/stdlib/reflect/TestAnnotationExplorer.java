package io.huangsam.trial.stdlib.reflect;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static io.huangsam.trial.stdlib.reflect.AnnotationExplorer.TrialMetadata;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for AnnotationExplorer.
 */
public class TestAnnotationExplorer {

    private AnnotationExplorer explorer;

    /**
     * Set up AnnotationExplorer before each test.
     */
    @BeforeEach
    public void setUp() {
        explorer = new AnnotationExplorer();
    }

    /**
     * Sample annotated class for testing purposes.
     */
    @TrialMetadata(value = "DemoClass", version = 2)
    static class DemoClass {
        @TrialMetadata("DemoMethod")
        public void demo() {
        }

        public void undemo() {
        }
    }

    @Test
    public void testGetClassMetadata() {
        Optional<TrialMetadata> metadata = explorer.getClassMetadata(DemoClass.class);
        assertTrue(metadata.isPresent());
        assertEquals("DemoClass", metadata.get().value());
        assertEquals(2, metadata.get().version());
    }

    @Test
    public void testGetAnnotatedMethodNames() {
        List<String> names = explorer.getAnnotatedMethodNames(DemoClass.class, TrialMetadata.class);
        assertEquals(1, names.size());
        assertTrue(names.contains("demo"));
    }
}
