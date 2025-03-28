package datastructures;
// Description: This program is a graph that finds the sinks of a graph, 
// counts the in-degree of a vertex, prints the graph, finds all paths, 
// finds the total weight of the graph, and finds the shortest paths from a 
// vertex to all other vertices.

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class Graph {

    private class Edge {
        String target;
        int weight;

        private Edge(String target, int weight) {
            this.target = target;
            this.weight = weight;
        }

        public String toString() {
            return this.target + "(" + this.weight + ")";
        }
    }

    private Map<String, ArrayList<Edge>> adjacencyList;
    
    public Graph() {
        adjacencyList = new HashMap<String, ArrayList<Edge>>();
    }
    
    public void addVertex(String vertex) {
        adjacencyList.putIfAbsent(vertex, new ArrayList<Edge>());
    }
    
    public void addEdge(String from, String to, int weight) {
        adjacencyList.get(from).add(new Edge(to, weight));
    }

    public List<String> findSinks() {
        List<String> sinks = new ArrayList<>();
        for (String vertex : adjacencyList.keySet()) {
            if (adjacencyList.get(vertex).isEmpty()) {
                sinks.add(vertex);
            }
        }
        return sinks;
    }

    public int countInDegree(String vertex) {
        int inDegree = 0;
        for (String fromVertex : adjacencyList.keySet()) {
            for (Edge edge : adjacencyList.get(fromVertex)) {
                if (edge.target.equals(vertex)) {
                    inDegree++;
                }
            }
                inDegree++;
            }
        return inDegree;
        }

    public void print() {
        for (String vertex : adjacencyList.keySet()) {
            System.out.println(vertex + " -> " + adjacencyList.get(vertex));
        }
    }

    // taken from last week's dfs
    public void allPaths(String start, ArrayList<String> visited, ArrayList<ArrayList<String>> allPaths) {
        visited.add(start);
        boolean isLeaf = true;
        for (Edge neighbour : adjacencyList.get(start)) {
            if (!visited.contains(neighbour.target)) {
                isLeaf = false;
                allPaths(neighbour.target, new ArrayList<>(visited), allPaths);
            }
        }
        if (isLeaf) {
            allPaths.add(visited);
        }
    }

    public int totalWeight() {
        int weight = 0;
        for (String vertex : adjacencyList.keySet()) {
            weight += adjacencyList.get(vertex).size();
        }
        return weight;
    }

    // taken from lecture
    public Map<String, Integer> dijkstra(String start) {
        Map<String, Integer> distances = new HashMap<>(); // distances from start
        ArrayList<String> visited = new ArrayList<>(); // vertices visited
        Map<String, ArrayList<Edge>> graph = adjacencyList; // the graph
        
        for (String node : graph.keySet()) {
            distances.put(node, Integer.MAX_VALUE);		//initialize the distances
        }
        
        distances.put(start, 0);
        
        while (visited.size() < graph.size()) {		//while there are vertices left to visit
            String closestNode = null;
            int smallest = Integer.MAX_VALUE;
        for (String node : distances.keySet()) {	// find the closest unvisited vertex, initially start
                 if (!visited.contains(node) && distances.get(node) < smallest) {
                    closestNode = node;
                    smallest = distances.get(node);
                }
            }
            if (closestNode == null) break;
            visited.add(closestNode);				// mark vertex as visited
            for (Edge neighbor : graph.getOrDefault(closestNode, new ArrayList<>())) { 	// for each neighbour
                if (!visited.contains(neighbor.target)) {						// if not visited
                    int newDist = distances.get(closestNode) + neighbor.weight;		// update distances
                    if (newDist < distances.get(neighbor.target)) {
                        distances.put(neighbor.target, newDist);
                    }
                }
            }
        }
        return distances;
    }

    public void shortestPaths(String start) {
        System.out.println("The shortest paths from " + start + " to:");
        Map<String, Integer> paths = dijkstra(start);
        for (String vertex : paths.keySet()) {
            System.out.println("\t" + vertex + ": " + paths.get(vertex));
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph();

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");
        graph.addVertex("F");
        graph.addVertex("G");

        graph.addEdge("A", "B", 1);
        graph.addEdge("A", "F", 1);
        graph.addEdge("B", "C",4);
        graph.addEdge("C", "A",1);
        graph.addEdge("C", "D",3);
        graph.addEdge("C", "E",2);
        graph.addEdge("D", "E",1);
        graph.addEdge("D", "F",3);
        graph.addEdge("D", "G",1);
        graph.addEdge("E", "F",1);
        graph.addEdge("F", "D",1);

        graph.print();

        /*
        System.out.println("Sinks? " + graph.findSinks());
        System.out.println("A: " + graph.countInDegree("A"));
        System.out.println("B: " + graph.countInDegree("B"));
        System.out.println("C: " + graph.countInDegree("C"));
        System.out.println("D: " + graph.countInDegree("D"));
        System.out.println("E: " + graph.countInDegree("E"));
        System.out.println("F: " + graph.countInDegree("F"));
        System.out.println("G: " + graph.countInDegree("G"));
        */

        /*
        ArrayList<ArrayList<String>> paths = new ArrayList<>();
        graph.allPaths("A", new ArrayList<String>(), paths);

        for (ArrayList<String> path : paths) {
            System.out.println(path);
        }
        */

        graph.shortestPaths("A");
    }
}