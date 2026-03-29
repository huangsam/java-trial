package io.huangsam.trial.libs.guava;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SuppressWarnings("UnstableApiUsage")
public class TestNetworkArchitect {

    @Test
    void testDependencyGraph() {
        NetworkArchitect architect = new NetworkArchitect();
        
        // App depends on LibA and LibB
        architect.addDependency("App", "LibA");
        architect.addDependency("App", "LibB");
        // LibA depends on LibC
        architect.addDependency("LibA", "LibC");

        Set<String> appDeps = architect.getDependenciesOf("App");
        assertEquals(2, appDeps.size());
        assertTrue(appDeps.contains("LibA"));
        assertTrue(appDeps.contains("LibB"));

        Set<String> libADeps = architect.getDependenciesOf("LibA");
        assertEquals(1, libADeps.size());
        assertTrue(libADeps.contains("LibC"));

        Set<String> libCDependents = architect.getDependentsOf("LibC");
        assertEquals(1, libCDependents.size());
        assertTrue(libCDependents.contains("LibA"));
    }
}
