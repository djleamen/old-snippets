// Description: Breadth First Search and Depth First Search algorithms

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class bfsdfs {

    HashMap<String, ArrayList<String>> adjacencyList;

    public bfsdfs() {
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

    public void bfs(String start) {
        Queue<String> queue = new LinkedList<>();
        ArrayList<String> visited = new ArrayList<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            System.out.println(current);
            for (String s : adjacencyList.get(current)) {
                if (!visited.contains(s)) {
                    queue.add(s);
                    visited.add(s);
                }
            }
        }
    }

    public void dfs(String start, ArrayList<String> visited, ArrayList<ArrayList<String>> paths) {
        if (visited.contains(start)) {
            return;
        }
        visited.add(start);

        boolean end = true;

        for (String s : adjacencyList.get(start)) {
            if (!visited.contains(s)) {
                end = false;
                dfs(s, new ArrayList<>(visited), paths);
            }
        }
        if (end) {
            paths.add(visited);
        }
    }
    

    /*
     * public void dfsAllPaths(String start, ArrayList<String> visited, ArrayList<ArrayList<String>> allPaths) {
        visited.add(start);

        // Check if all neighbors are visited or there's no neighbor to proceed
        boolean isLeaf = true;
        for (String neighbor : adjacencyList.get(start)) {
            if (!visited.contains(neighbor)) {
                isLeaf = false;
                dfsAllPaths(neighbor, new ArrayList<>(visited), allPaths);
            }
        }

        // If it's a leaf node or all neighbors are visited, add the current path to allPaths
        if (isLeaf) {
            allPaths.add(visited);
        }
    }
     */

    public static void main(String[] args) {
        bfsdfs graph = new bfsdfs();

        // create our vertices
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");
        graph.addVertex("F");
        graph.addVertex("G");

        // what is A connected to
        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("A", "F");

        // what is B connected to (don't need to dupe edges that already exist!)
        graph.addEdge("B", "C");

        // C
        graph.addEdge("C", "D");
        graph.addEdge("C", "E");

        // D
        graph.addEdge("D", "E");
        graph.addEdge("D", "F");
        graph.addEdge("D", "G");

        // E
        graph.addEdge("E", "F");

        //graph.bfs("A");
        ArrayList<ArrayList<String>> paths = new ArrayList<>();
        graph.dfs("A", new ArrayList<String>(), paths);

        for (ArrayList<String> path : paths) {
            System.out.println(path);
        }
    }

}