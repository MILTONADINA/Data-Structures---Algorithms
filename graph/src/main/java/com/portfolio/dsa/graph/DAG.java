package com.portfolio.dsa.graph;

	import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

	public class DAG<T> {
		
		
		public class WeightedPath<T> {
		    private int totalWeight;
		    private List<T> path;

		    public WeightedPath(int totalWeight, List<T> path) {
		        this.totalWeight = totalWeight;
		        this.path = path;
		    }

		    public int getTotalWeight() {
		        return totalWeight;
		    }

		    public List<T> getPath() {
		        return path;
		    }
		}
		
//keys are node(cities)
//values are lists of edges(connections)
	    private Map<T, List<Edge<T>>> graph = new HashMap<>();
		// add node to graph if not there 
		//also the node has empty list of edges
	    public void addNode(T node) {
	        graph.putIfAbsent(node, new ArrayList<>());
	    }
	    
	    
	    //add starts and destination nodes to the graph
	    //and add the edge to the list of edges for the source node
		
	    public void addEdge(T source, T destination, int weight) {
	        graph.get(source).add(new Edge<>(source, destination, weight));
	    }

	    public List<T> shortestPath(T start, T end) {
	        Map<T, Integer> distance = new HashMap<>();//A map. to store shortest known distance start node to each node in the graph.
	        Map<T, T> parent = new HashMap<>();//A map that will store the parent node for each node in the shortest path.
	        PriorityQueue<Edge<T>> pq = new PriorityQueue<>();//queue of edges, sorted by weights.
	        
			//set initial distance to infinity, except for the start node.
	        for (T node : graph.keySet()) {
	            distance.put(node, Integer.MAX_VALUE);
	        }

	        distance.put(start, 0);
	       // Add start node to priority queue with  weight 0. 
	        pq.offer(new Edge<>(null, start, 0));
	       
	        //explores graph and updates shortest known distances to reach each node from the starting position.
	        while (!pq.isEmpty()) {
	            Edge<T> currentEdge = pq.poll();
	            T currentNode = currentEdge.destination;

	            if (currentEdge.weight > distance.get(currentNode)) {
	                continue;
	            }

				for (Edge<T> neighborEdge : graph.get(currentNode)) {
	                T neighborNode = neighborEdge.destination;
	                int distanceFromCurrent = distance.get(currentNode) + neighborEdge.weight;

	                if (distanceFromCurrent < distance.get(neighborNode)) {
	                    distance.put(neighborNode, distanceFromCurrent);
	                    parent.put(neighborNode, currentNode);
	                    pq.offer(new Edge<>(currentNode, neighborNode, distanceFromCurrent));
	                }
	            }
	        }

	        List<T> path = new ArrayList<>();
	        T currentNode = end;

	        while (currentNode != null) {
	            path.add(currentNode);
	            currentNode = parent.get(currentNode);
	        }

	        Collections.reverse(path);//back-track through the parent array till we reach the node 
	        return path;
	    }
	    
	    // Calculate the total weight of a given path
	    public int calculatePathWeight(List<T> path) {
	        int totalWeight = 0;
	        T currentNode = path.get(0); // Start from the first node in the path

	        for (int i = 1; i < path.size(); i++) {
	            T nextNode = path.get(i);
	            List<Edge<T>> edges = graph.get(currentNode);

	            // Find the edge from the current node to the next node
	            for (Edge<T> edge : edges) {
	                if (edge.destination.equals(nextNode)) {
	                    totalWeight += edge.weight;
	                    break;
	                }
	            }

	            currentNode = nextNode;
	        }

	        return totalWeight;
	    }

	  
// to get graph 
	    private static class Edge<T> implements Comparable<Edge<T>> {
	        private T source;
	        private T destination;
	        private int weight;

	        public Edge(T source, T destination, int weight) {
	            this.source = source;
	            this.destination = destination;
	            this.weight = weight;
	        }

	        @Override
	        public int compareTo(Edge<T> other) {
	            return Integer.compare(weight, other.weight);
	        }
	    }
	}



