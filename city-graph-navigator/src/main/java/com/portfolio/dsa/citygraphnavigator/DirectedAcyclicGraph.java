package com.portfolio.dsa.citygraphnavigator;

import java.util.*;

// Generic Graph Class
// Note: While the assignment mentions DAG, the requirement to add bidirectional edges [cite: 8]
// makes it function as an undirected graph for pathfinding purposes.
public class DirectedAcyclicGraph<T> {
    private Map<T, List<Edge<T>>> adjacencyList;
    private Map<String, T> nodeMap; // Helper map to get node object from its code/ID (assuming T has a getCode() method or similar)

    public DirectedAcyclicGraph() {
        this.adjacencyList = new HashMap<>();
        this.nodeMap = new HashMap<>(); // Initialize the helper map
    }

    // Method to add a node (City) - Requires a way to get a unique String ID (like city code)
    // This example assumes T has a method like getCode() or you pass the code separately.
    public void addNode(String code, T node) {
        adjacencyList.putIfAbsent(node, new ArrayList<>());
        nodeMap.put(code, node); // Store the node with its code
    }

    // Method to get a node by its code
     public T getNode(String code) {
        return nodeMap.get(code);
    }


    // Method to add a weighted edge
    public void addEdge(T source, T destination, int weight) {
        // Ensure nodes exist in the adjacency list
        adjacencyList.putIfAbsent(source, new ArrayList<>());
        adjacencyList.putIfAbsent(destination, new ArrayList<>());

        // Add edge from source to destination
        adjacencyList.get(source).add(new Edge<>(destination, weight));
    }

     // Dijkstra's algorithm to find the shortest path [cite: 6]
    public Map<String, Object> findShortestPath(T startNode, T endNode) {
        Map<T, Integer> distances = new HashMap<>();
        Map<T, T> predecessors = new HashMap<>();
        PriorityQueue<Map.Entry<T, Integer>> pq = new PriorityQueue<>(Map.Entry.comparingByValue());

        // Initialize distances
        for (T node : adjacencyList.keySet()) {
            distances.put(node, Integer.MAX_VALUE);
        }
         if (!adjacencyList.containsKey(startNode)) {
             return Collections.emptyMap(); // Start node not in graph
         }
        distances.put(startNode, 0);

        pq.add(new AbstractMap.SimpleEntry<>(startNode, 0));

        while (!pq.isEmpty()) {
            Map.Entry<T, Integer> currentEntry = pq.poll();
            T currentNode = currentEntry.getKey();
            int currentDistance = currentEntry.getValue();

            // If we already found a shorter path or distance is MAX_VALUE, skip optimization
            if (currentDistance > distances.getOrDefault(currentNode, Integer.MAX_VALUE)) {
                continue;
            }


            // Explore neighbors
            List<Edge<T>> neighbors = adjacencyList.getOrDefault(currentNode, Collections.emptyList());
            for (Edge<T> edge : neighbors) {
                T neighbor = edge.getDestination();
                // Ensure neighbor is actually in the distance map before proceeding
                if (distances.containsKey(neighbor)) {
                    int newDist = currentDistance + edge.getWeight();

                    if (newDist < distances.get(neighbor)) {
                        distances.put(neighbor, newDist);
                        predecessors.put(neighbor, currentNode);
                        pq.add(new AbstractMap.SimpleEntry<>(neighbor, newDist));
                    }
                }
            }
        }

        // Prepare result map
        Map<String, Object> result = new HashMap<>();
        Integer finalDistance = distances.get(endNode);


        // Reconstruct path
        List<T> path = new LinkedList<>();
         if (finalDistance != null && finalDistance != Integer.MAX_VALUE) { // Check if endNode is reachable
            T step = endNode;
            while (step != null) {
                path.add(0, step); // Add to the beginning of the list
                step = predecessors.get(step);
            }
         } else {
            finalDistance = -1; // Indicate no path found or endNode not in graph
            path = Collections.emptyList(); // Empty path
         }

        result.put("distance", finalDistance);
        result.put("path", path);

        return result;
    }
}

