// Description: A program that creates a list of the first 10 powers of 2 and then prints them out.

import java.util.*;
import java.lang.Math;

public class PowOf2 {
    public static void main(String[] args) {
        List<Integer> intlist = new ArrayList<>();
        for (int i = 0; i < 10; i++) { 
            intlist.add(i, (int)(Math.pow(2, i)));
        }
        for (int i = 0; i < intlist.size(); i++) {
            System.out.println(intlist.get(i));
        }
    }
}