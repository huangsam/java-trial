package io.huangsam.trial.features.modern;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LoomThreadsTest {

    @Test
    void testVirtualThreads() throws InterruptedException {
        LoomThreads loom = new LoomThreads();
        int taskCount = 100; // Small number for unit test efficiency
        int completed = loom.runManyVirtualThreads(taskCount);
        
        assertEquals(taskCount, completed, "All virtual thread tasks should complete");
    }
}
