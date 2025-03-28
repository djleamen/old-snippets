// Description: A program to manage and analyze the campus map of the Oshawa Campus.

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * A program to manage and analyze the campus map of the Oshawa Campus.
 */
public class OshawaCampus {
    /**
     * The main method to run the Oshawa campus map analysis.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        CampusMap Oshawa = readOshawa();

        // Print the campus map
        Oshawa.print();

        // Choose start and end locations - feel free to edit the building codes to try different combos
        Location start = getLocationByCode(Oshawa.adj, "CFH");
        Location end = getLocationByCode(Oshawa.adj, "JW");
        Access studentOT = Access.OT;
        Access studentDC = Access.DC;

        // Print the shortest path for OT students
        printShortestPath(Oshawa.shortestRoute(start, end, studentOT), studentOT);
        // Print the shortest path for DC students
        printShortestPath(Oshawa.shortestRoute(start, end, studentDC), studentDC);

        // Print all paths for OT students
        printAllPaths(Oshawa.findAllRoutes(start, end, studentOT), studentOT);
        // Print all paths for DC students
        printAllPaths(Oshawa.findAllRoutes(start, end, studentDC), studentDC);

    }

    /**
     * Prints all possible paths between two locations for a given set of permissions.
     *
     * @param paths       a list of all possible paths
     * @param permissions the access permissions (OT, DC, or AL)
     */
    public static void printAllPaths(List<List<Location>> paths, Access permissions) {
        if (paths.isEmpty()) {
            System.out.println("There are no allowable paths between these locations for " + permissions + " students");
            return;
        }
        int i = 0;
        Location start = paths.get(0).get(0);
        Location end = paths.get(0).get(paths.get(0).size() - 1);
        System.out.println("All paths from " + start + " to " + end + " for " + permissions + " students:");
        for (List<Location> path : paths) {
            System.out.println(i++ + ". " + path);
        }
    }

    /**
     * Prints the shortest path between two locations for a given set of permissions.
     *
     * @param path        the shortest path
     * @param permissions the access permissions (OT, DC, or AL)
     */
    public static void printShortestPath(List<Location> path, Access permissions) {
        if (path == null) {
            System.out.println("There is no allowable path between these locations for " + permissions + " students");
            return;
        }
        Location start = path.get(0);
        Location end = path.get(path.size() - 1);
        System.out.println("The shortest path from " + start + " to " + end + " for " + permissions + " students is:");
        for (int i = 0; i < path.size(); i++) {
            System.out.println("\t" + i + ". " + path.get(i));
        }
    }

    /**
     * Retrieves a location by its code from the campus map.
     *
     * @param adj  the adjacency list representing the campus map
     * @param code the code of the location to retrieve
     * @return the location with the specified code, or null if not found
     */
    public static Location getLocationByCode(Map<Location, List<Location>> adj, String code) {
        for (Location location : adj.keySet()) {
            if (location.getCode().equals(code)) {
                return location;
            }
        }
        return null; // Return null if no Location with the specified code is found
    }

    /**
     * Reads the campus map information from a file and constructs the campus map.
     *
     * @return the campus map constructed from the file
     */
    public static CampusMap readOshawa(){
        CampusMap map = new CampusMap();
        try {
            Scanner in = new Scanner(new File("map.txt"));
            String line = in.nextLine();
            // add locations from file
            while (!line.isEmpty()) {
                String[] values = line.split(", ");
                int permission = Integer.parseInt(values[0]);
                String name = values[1];
                String code = values[2];
                Access accessLevel = Access.AL;
                if (permission == 1) {
                    accessLevel = Access.OT;
                } else if (permission == 2) {
                    accessLevel = Access.DC;
                }
                map.addLocation(new Location(name, code, accessLevel));
                line = in.nextLine();
            }

            // add paths from file
            while (in.hasNext()) {
                String start = in.next();
                Location startLoc = getLocationByCode(map.adj, start);
                String end = in.next();
                Location endLoc = getLocationByCode(map.adj, end);
                map.addPath(startLoc, endLoc);
            }
        } catch (Exception e) {
            System.out.println("File Exception");
            System.exit(-1);
        }
        return map;
    }
}
