package datastructures;
// Define a class that contains a main method that prints out the first 25 powers of 2.

import java.lang.Math;

public class PowersOf2 {
    public static void main(String[] args) {
        int powers[] = new int[25];
        for(int i = 0; i < 25; i++) {
            powers[i] = (int)(Math.pow(2, i));
        }
        for (int i = 0; i < 25; i++) {
            System.out.println(powers[i]);
        }
    }
}