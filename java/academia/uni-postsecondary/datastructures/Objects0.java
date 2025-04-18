// Description: This program prints a 2D grid with 5 points on it. 
// The points are represented by asterisks.

import java.awt.*;

public class Objects0 {

    public static void print(Point a, Point b, Point c, Point d, Point e) {
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 11; y++) {
                System.out.print("- ");
            }
            System.out.println();
            for(int y = 4; y > -1; y--) {
                System.out.print("| ");
                // we know x, y is our loc
                if ((a.getX() == x && a.getY() == y) || (b.getX() == x && b.getY() == y) || (c.getX() == x && c.getY() == y) || (d.getX() == x && d.getY() == y) || (e.getX() == x && e.getY() == y)) {
                    System.out.print("* ");
                } else {
                    System.out.print("  ");
                }
            }
            System.out.print("  ");
        }
        System.out.println("|");
        for (int y = 0; y < 11; y++) {
            System.out.print("- ");
        }
    }

    public static void main(String[] args) {
        Point a = new Point(1, 1);
        Point b = new Point(2, 3);
        Point c = new Point(5, 2);
        Point d = new Point(4, 1);
        Point e = new Point(2, 2);

        print(a, b, c, d, e);
    } 
 }
        
