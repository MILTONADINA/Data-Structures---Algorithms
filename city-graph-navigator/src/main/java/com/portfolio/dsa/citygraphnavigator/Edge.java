package com.portfolio.dsa.citygraphnavigator;

// Class to represent an Edge in the graph
public class Edge<T> {
    T destination;
    int weight; // Distance

    public Edge(T destination, int weight) {
        this.destination = destination;
        this.weight = weight;
    }

    public T getDestination() {
        return destination;
    }

    public int getWeight() {
        return weight;
    }
}

