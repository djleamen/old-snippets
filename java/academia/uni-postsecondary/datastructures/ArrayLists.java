// Description: This program demonstrates the use of ArrayLists in Java.

import java.util.ArrayList;
import java.lang.Math;

public class ArrayLists {
    public static void main(String[] args) {
        ArrayList<Double> arrlist = new ArrayList<>();
        arrlist.add(47.2);
        arrlist.add(73.6);
        arrlist.add(64.5);
        arrlist.add(3.14159);
        arrlist.add(0.0);

        System.out.println(arrlist);

        arrlist.add(3, -0.7);
        System.out.println(arrlist);
        System.out.println(arrlist.get(3));
        for (int i = 0; i < arrlist.size()-1; i++) {
            arrlist.set(i, (Math.random() * 100) + 1);
        }
        System.out.println(arrlist);

        System.out.println("List contains 5.5? " + (arrlist.contains((5.5)) ? "Yes" : "No"));
        System.out.println("List contains 5.5? " + (arrlist.contains((0.0)) ? "Yes" : "No"));
    }
}