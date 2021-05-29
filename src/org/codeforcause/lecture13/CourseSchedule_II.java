package org.codeforcause.lecture13;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

class GraphSort {
    HashMap<Integer, Vertex> vertices = new HashMap<Integer, Vertex>();

    class Vertex {
        int val;
        HashSet<Vertex> neighbours;
        Vertex(int val) {
            this.val = val;
            this.neighbours = new HashSet<>();
        }
    }

    public void addVertex(int val) {
        Vertex vertex = new Vertex(val);
        vertices.put(val, vertex);
    }

    public void addEdge(int f, int s) {
        HashSet<Vertex> set1;
        Vertex v1, v2;
        if (vertices.containsKey(f)) {
            set1 = vertices.get(f).neighbours;
        } else {
            v1 = new Vertex(f);
            set1 = new HashSet<>();
            v1.neighbours = set1;
            vertices.put(f,v1);
        }
        if (vertices.containsKey(s)) {
            v2 = vertices.get(s);
        } else {
            v2 = new Vertex(s);
            vertices.put(s,v2);
        }
        set1.add(v2);
    }

    public int[] topologicalSort() {
        int[] sortedVertices = new int[vertices.size()];
        Stack<Integer> stack = new Stack<>();
        HashSet<Vertex> visited = new HashSet<>();
        for (Vertex vertex : vertices.values()) {
            if (visited.contains(vertex)) {
                continue;
            }
            traverse(vertex, stack, visited);
        }
        int i = 0;
        while (!stack.isEmpty()) {
            sortedVertices[i++] = stack.pop();
        }
        return sortedVertices;
    }

    private void traverse(Vertex vertex, Stack<Integer> stack, HashSet<Vertex> visited) {
        visited.add(vertex);
        for (Vertex padosi : vertex.neighbours) {
            if (!visited.contains(padosi)) {
                traverse(padosi, stack, visited);
            }
        }
        stack.push(vertex.val);
    }

    public boolean isCyclic() {
        HashSet<Vertex> whiteSet;
        HashSet<Vertex> graySet = new HashSet<>();
        HashSet<Vertex> blackSet = new HashSet<>();
        whiteSet = new HashSet<>(vertices.values());

        for (Vertex vertex : whiteSet) {
            if (blackSet.contains(vertex) || graySet.contains(vertex)) {
                continue;
            }
            if (dfs(vertex, graySet, blackSet)) {
                return true;
            }
        }
        return false;
    }

    private boolean dfs(Vertex vertex, HashSet<Vertex> graySet, HashSet<Vertex> blackSet) {
        graySet.add(vertex);
        for (Vertex padosi : vertex.neighbours) {
            if (blackSet.contains(padosi)) {
                continue;
            }
            if (graySet.contains(padosi)) {
                return true;
            }
            if (dfs(padosi, graySet, blackSet)) {
                return true;
            }
        }
        graySet.remove(vertex);
        blackSet.add(vertex);
        return false;
    }
}

public class CourseSchedule_II {
    public static void main(String[] args) {
        int numCourses = 4;
        int[][] prerequisites = {{1,0},{2,0},{3,1},{3,2}};
        System.out.println(Arrays.toString(findOrder(numCourses, prerequisites)));
    }

    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        GraphSort graph = new GraphSort();
        for (int i = 0; i < numCourses; i++) {
            graph.addVertex(i);
        }
        for (int[] prerequisite : prerequisites) {
            graph.addEdge(prerequisite[1], prerequisite[0]);
        }
        if (graph.isCyclic()) {
            return new int[0];
        }
        return graph.topologicalSort();
    }
}
