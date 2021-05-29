package org.codeforcause.lecture13;

import java.util.HashMap;
import java.util.HashSet;

class Graph {
    HashMap<Integer, Vertex> vertices = new HashMap<Integer, Vertex>();
    class Vertex {
        int val;
        HashSet<Vertex> neighbours;
        Vertex(int val) {
            this.val = val;
            this.neighbours = new HashSet<>();
        }
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

public class CourseSchedule_I {
    public static void main(String[] args) {
        int numCourses = 2;
        int[][] prerequisites = {{1,0},{0,1}};
        System.out.println(canFinish(numCourses, prerequisites));
    }

    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        Graph graph = new Graph();
        for (int[] prerequisite : prerequisites) {
            graph.addEdge(prerequisite[1], prerequisite[0]);
        }
        return !graph.isCyclic();
    }
}
