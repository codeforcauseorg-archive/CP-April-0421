package org.codeforcause.lecture10;

import java.util.*;

public class Graph <E extends Comparable<E>> {
    private Map<E, Vertex> vertices = new HashMap<>();

    private class Vertex {
        E value;
        Map<Vertex, Integer> neighbours;
        public Vertex(E value) {
            this.value = value;
            this.neighbours = new HashMap<>();
        }

        public void addNeighbours(Vertex vertex, int cost) {
            this.neighbours.put(vertex, cost);
        }
    }

    private class Edge {
        Vertex start, end;
        Integer cost;
        public Edge(Vertex start, Vertex end, Integer cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }
    }

    public void addVertex(E value) {
        if (!vertices.containsKey(value)) {
            vertices.put(value, new Vertex(value));
        }
    }

    public void addEdge(E start, E end, Integer cost) {
        Vertex s = vertices.get(start);
        Vertex e = vertices.get(end);
        if (s == null) {
            addVertex(start);
            s = vertices.get(start);
        }
        if (e == null) {
            addVertex(end);
            e = vertices.get(end);
        }
        s.addNeighbours(e, cost);
        e.addNeighbours(s, cost);
    }

    public void DFT(E start) {
        Vertex v = vertices.get(start);
        Stack<Vertex> stack = new Stack<>();
        Set<Vertex> visited = new HashSet<>();
        stack.push(v);
        visited.add(v);
        while (!stack.isEmpty()) {
            Vertex currVertex = stack.pop();
            System.out.println(currVertex.value + " ");
            for (Vertex padosi : currVertex.neighbours.keySet()) {
                if (!visited.contains(padosi)) {
                    stack.push(padosi);
                    visited.add(padosi);
                }
            }
        }
    }

    public void BFT(E start) {
        Vertex v = vertices.get(start);
        Queue<Vertex> queue = new LinkedList<>();
        Set<Vertex> visited = new HashSet<>();
        queue.add(v);
        visited.add(v);
        while (!queue.isEmpty()) {
            Vertex currVertex = queue.remove();
            System.out.println(currVertex.value + " ");
            for (Vertex padosi : currVertex.neighbours.keySet()) {
                if (!visited.contains(padosi)) {
                    queue.add(padosi);
                    visited.add(padosi);
                }
            }
        }
    }

}
