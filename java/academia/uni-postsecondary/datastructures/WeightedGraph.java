import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class WeightedGraph {

    HashMap<String, ArrayList<String>> adjacencyList;

    public WeightedGraph() {
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

    public void bfs(String start, String goal) {
        Queue<String> queue = new LinkedList<>();
        ArrayList<String> visited = new ArrayList<>();
        HashMap<String, String> parents = new HashMap<>();

        queue.add(start);
        visited.add(start);
        parents.put(start, null);

        while (!queue.isEmpty()) {
            String current = queue.poll();

            if (current.equals(goal)) {
                reconstructPath(parents, start, goal);
                return;
            }
            
            for (String s : adjacencyList.get(current)) {
                if (!visited.contains(s)) {
                    queue.add(s);
                    visited.add(s);
                    parents.put(s, current); 
                }
            }
        }
    }

    // helper method
    private void reconstructPath(HashMap<String, String> parents, String start, String goal) {
        LinkedList<String> path = new LinkedList<>();
        String current = goal;
        
        while (current != null) {
            path.addFirst(current); // Add to the front to reverse the order
            current = parents.get(current);
        }
        
        System.out.println("Shortest path from " + start + " to " + goal + ": " + path);
    }


    public static void main(String[] args) {
        WeightedGraph graph = new WeightedGraph();

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

        graph.bfs("A", "D");
    }

}