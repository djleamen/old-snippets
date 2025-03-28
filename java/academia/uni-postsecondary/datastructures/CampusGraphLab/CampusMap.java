import java.util.*;

/**
 * Represents a campus map with locations and pathways.
 */
public class CampusMap {
    protected Map<Location, List<Location>> adj = new HashMap<>();

    /**
     * Adds a location to the campus map.
     *
     * @param loc the location to be added
     */
    public void addLocation(Location loc) {
        // if the adjacency list doesn't contain the given location, add it
        if (!adj.containsKey(loc)) {
            adj.put(loc, new ArrayList<>());
        }
    }

    /**
     * Adds a pathway between two locations.
     *
     * @param loc1 the first location
     * @param loc2 the second location
     */
    public void addPath(Location loc1, Location loc2) {
        addLocation(loc1);
        addLocation(loc2);
        // add a path between loc 1 and loc 2 and vice versa
        adj.get(loc1).add(loc2);
        adj.get(loc2).add(loc1);
    }
	
	    /**
     * Prints the campus map with all locations and their connected pathways.
     */
    public void print() {
        // for each entry in the adjacency list, get the key and its neighbours
        for (Map.Entry<Location, List<Location>> entry : adj.entrySet()) { 
            Location loc = entry.getKey(); 
            List<Location> neighbours = entry.getValue();  
            System.out.print(loc.getName() + " has a pathway to: ");  
            // print out the neighbours iteratively          
            for (int i = 0; i < neighbours.size(); i++) {
                System.out.print(neighbours.get(i).getName());
                if (i < neighbours.size() - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println();
        }
    }

    /**
     * Finds the shortest route between two locations based on the specified access level.
     *
     * @param start  the starting location
     * @param end    the destination location
     * @param access the access level (OT, DC, or AL)
     * @return the shortest route between the start and end locations, or null if no route is found
     */
    public List<Location> shortestRoute(Location start, Location end, Access access) {
        // if our start location is the end location then return the list of start
        if (start.equals(end)) return List.of(start);
        // create a queue of locations and a set of visited locations
        Queue<List<Location>> queue = new LinkedList<>();
        Set<Location> visited = new HashSet<>();
        // add the list of start to our queue to visit
        queue.offer(List.of(start));
        // add start to our visited list
        visited.add(start);

        // while we still have more locations to visit
        while (!queue.isEmpty()) {
            // add our queue to our path and set our current location
            List<Location> path = queue.poll();
            Location current = path.get(path.size() - 1);
            // for each neighbouring location, check if we haven't visited and it's accessible to us
            for (Location neighbour : adj.getOrDefault(current, new ArrayList<>())) {
                if (visited.contains(neighbour) || !isAccessible(neighbour, access)) continue;
                // create a new path list and add our existing path to this
                List<Location> newPath = new ArrayList<>(path);
                // add the neighbour
                newPath.add(neighbour);
                // if we've reached the end then that completes the new path
                if (neighbour.equals(end)) return newPath;

                // add the newpath to our queue
                queue.offer(newPath);
                // add the neighbour to our visited list
                visited.add(neighbour);
            }
        }
        // if no path exists
        return null; 
    }

    /**
     * Finds all possible routes between two locations based on the specified access level.
     *
     * @param start  the starting location
     * @param end    the destination location
     * @param access the access level (OT, DC, or AL)
     * @return a list of all possible routes between the start and end locations
     */
    public List<List<Location>> findAllRoutes(Location start, Location end, Access access) {
        // create a new list to hold all paths
        List<List<Location>> allPaths = new ArrayList<>();
        // find every path using the DFS function
        findAllPathsDFS(start, end, access, new HashSet<>(), new ArrayList<>(), allPaths);
        return allPaths;
    }

    /**
     * Finds all possible paths between two locations based on the specified access level using Depth-First Search.
     *
     * @param current the current location being explored
     * @param end     the destination location
     * @param access  the access level (OT, DC, or AL)
     * @param visited a set of visited locations to avoid revisiting
     * @param path    the current path being explored
     * @param allPaths a list to store all found paths
     */
    private void findAllPathsDFS(Location current, Location end, Access access, Set<Location> visited, List<Location> path, List<List<Location>> allPaths) {
        // add the current location to the visited set and make it the start of the path
        visited.add(current);
        path.add(current);
        // if the current location is the end of our path then add this to the allPaths list
        if (current.equals(end)) {
            allPaths.add(new ArrayList<>(path));
        } else {
            // for every neighbour to our location, if we haven't visited it yet and
            // it's accessible to us, then recursively visit with this neighbour as our new current
            for (Location neighbor : adj.getOrDefault(current, new ArrayList<>())) {
                if (!visited.contains(neighbor) && isAccessible(neighbor, access)) {
                    findAllPathsDFS(neighbor, end, access, visited, path, allPaths);
                }
             }
        }
        // for tidiness
        path.remove(path.size() - 1);
        visited.remove(current);
    }

    // helper to check if a location is accessible for a given access level
    private boolean isAccessible(Location location, Access access) {
        return location.getAccess() == access || location.getAccess() == Access.AL;
    }
}
