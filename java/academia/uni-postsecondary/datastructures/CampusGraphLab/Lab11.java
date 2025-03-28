// Description: This program demonstrates the use of the CampusMap class to create a graph of locations on a campus.

public class Lab11 {
    public static void main(String[] args) {
        Location a = new Location("A Bldg", "A", Access.AL);
        Location b = new Location("B Bldg", "B", Access.AL);
        Location c = new Location("C Bldg", "C", Access.AL);
        Location d = new Location("D Bldg", "D", Access.AL);
        Location e = new Location("E Bldg", "E", Access.AL);
        Location f = new Location("F Bldg", "F", Access.AL);

        CampusMap campusMap = new CampusMap();

        campusMap.addLocation(a);
        campusMap.addLocation(b);
        campusMap.addLocation(c);
        campusMap.addLocation(d);
        campusMap.addLocation(e);
        campusMap.addLocation(f);

        campusMap.addPath(f, a);
        campusMap.addPath(f, d);
        campusMap.addPath(f, e);
        campusMap.addPath(d, e);
        campusMap.addPath(a, b);
        campusMap.addPath(e, b);
        campusMap.addPath(b, c);

        campusMap.print();
    }
}