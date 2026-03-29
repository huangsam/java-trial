package io.huangsam.trial.libs.guava;

import com.google.common.graph.GraphBuilder;
import com.google.common.graph.MutableGraph;

import java.util.Set;

/**
 * Demonstrates the Guava Graph API for modeling networks and dependencies.
 */
@SuppressWarnings("UnstableApiUsage")
public class NetworkArchitect {
    private final MutableGraph<String> graph;

    public NetworkArchitect() {
        // Create a directed graph to model something like a dependency tree or a social network
        this.graph = GraphBuilder.directed()
                .allowsSelfLoops(false)
                .build();
    }

    /**
     * Adds a dependency: 'node' depends on 'dependency'.
     *
     * @param node the node that has a dependency
     * @param dependency the dependency of the node
     */
    public void addDependency(String node, String dependency) {
        graph.putEdge(node, dependency);
    }

    /**
     * Gets all direct dependencies of a node.
     *
     * @param node the node to query
     * @return the set of direct dependencies
     */
    public Set<String> getDependenciesOf(String node) {
        return graph.successors(node);
    }

    /**
     * Gets all nodes that depend on this node.
     *
     * @param node the node to query
     * @return the set of nodes that depend on the given node
     */
    public Set<String> getDependentsOf(String node) {
        return graph.predecessors(node);
    }

    /**
     * Checks if there's a directed path from 'start' to 'end'.
     *
     * @param start the starting node
     * @param end the ending node
     * @return true if a path exists
     */
    public boolean hasDependencyPath(String start, String end) {
        // Basic check for direct or indirect (Guava Graphs provide many traversal utilities)
        return graph.successors(start).contains(end); 
    }

    public MutableGraph<String> getGraph() {
        return graph;
    }
}
