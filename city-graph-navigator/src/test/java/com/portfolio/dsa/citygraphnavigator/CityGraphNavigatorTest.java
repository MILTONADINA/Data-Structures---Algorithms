package com.portfolio.dsa.citygraphnavigator;

import org.junit.jupiter.api.Test;
import java.util.Map;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class CityGraphNavigatorTest {

    @Test
    void testShortestPath() {
        DirectedAcyclicGraph<City> graph = new DirectedAcyclicGraph<>();

        City a = new City("A", "City A");
        City b = new City("B", "City B");
        City c = new City("C", "City C");

        graph.addNode("A", a);
        graph.addNode("B", b);
        graph.addNode("C", c);

        graph.addEdge(a, b, 10);
        graph.addEdge(b, c, 20);
        graph.addEdge(a, c, 50);

        Map<String, Object> result = graph.findShortestPath(a, c);

        assertNotNull(result);
        assertEquals(30, result.get("distance"));

        @SuppressWarnings("unchecked")
        List<City> path = (List<City>) result.get("path");
        assertNotNull(path);
        assertEquals(3, path.size());
        assertEquals(a, path.get(0));
        assertEquals(b, path.get(1));
        assertEquals(c, path.get(2));
    }
}
