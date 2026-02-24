package com.portfolio.dsa.graph;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class DAGTest {

    @Test
    void testShortestPath() {
        DAG<String> graph = new DAG<>();
        graph.addNode("A");
        graph.addNode("B");
        graph.addNode("C");

        graph.addEdge("A", "B", 10);
        graph.addEdge("B", "C", 20);
        graph.addEdge("A", "C", 50);

        List<String> path = graph.shortestPath("A", "C");
        assertNotNull(path);
        assertEquals(3, path.size()); // A -> B -> C
        assertEquals("A", path.get(0));
        assertEquals("B", path.get(1));
        assertEquals("C", path.get(2));

        int weight = graph.calculatePathWeight(path);
        assertEquals(30, weight);
    }
}
