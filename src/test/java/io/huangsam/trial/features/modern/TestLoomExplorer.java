package io.huangsam.trial.features.modern;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestLoomExplorer {

    @Test
    void testVirtualThreads() throws InterruptedException {
        LoomExplorer explorer = new LoomExplorer();
        int taskCount = 100; // Small number for unit test efficiency
        int completed = explorer.runManyVirtualThreads(taskCount);
        
        assertEquals(taskCount, completed, "All virtual thread tasks should complete");
    }
}
