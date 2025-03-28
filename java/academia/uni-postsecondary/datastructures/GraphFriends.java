package datastructures;
// Description: A simple graph representation of a social media following network.

import java.util.ArrayList;
import java.util.HashMap;

public class GraphFriends {

    HashMap<String, ArrayList<String>> adjacencyList;

    public GraphFriends() {
        adjacencyList = new HashMap<String, ArrayList<String>>();
    }

    public void addVertex(String key) {
        adjacencyList.putIfAbsent(key, new ArrayList<String>());
    }

    public void addEdge(String v1, String v2) {
        adjacencyList.get(v1).add(v2);
        adjacencyList.get(v2).add(v1);
    }

    public void print() {
        for(String vertex : adjacencyList.keySet()) {
            System.out.println(vertex + " -> " + adjacencyList.get(vertex));
        }
    }

    public static void main(String[] args) {
        GraphFriends graph = new GraphFriends();

        // create our vertices
        graph.addVertex("DJ");
        graph.addVertex("Jacen");
        graph.addVertex("Jocelyn");
        graph.addVertex("Vanya");
        graph.addVertex("Joanna");
        graph.addVertex("Elias");
        graph.addVertex("Jaelyn");

        // DJ's following
        graph.addEdge("DJ", "Jacen");
        graph.addEdge("DJ", "Jocelyn");
        graph.addEdge("DJ", "Vanya");
        graph.addEdge("DJ", "Elias");

        // Jacen's following (don't need to dupe edges that already exist!)
        graph.addEdge("Jacen", "Jocelyn");
        graph.addEdge("Jacen", "Vanya");
        graph.addEdge("Jacen", "Jaelyn");

        // Jocelyn's following
        graph.addEdge("Jocelyn", "Joanna");
        graph.addEdge("Jocelyn", "Elias");

        // Vanya's following
        graph.addEdge("Vanya", "Jaelyn");

        // Joanna's following
        graph.addEdge("Joanna", "Jaelyn");

        System.out.println("Social media following graph:");
        graph.print();
    }

}