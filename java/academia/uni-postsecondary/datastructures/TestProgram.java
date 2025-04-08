/**
 * 
 * This is a test program to demonstrate the use of variables, if-else, and loops in Java
 * 
 * @author DJ Leamen
 * 
 */

 public class TestProgram {
    public static void main(String[] args) {
        int number = 12;
        boolean bool = true;
        double decimal = 0.2;
        String mystring = "hello";

        // testing int
        if (number < 10) {
            System.out.println(number + " is less than 10");
        } else {
            System.out.println(number + " is more than (or equal to) 10");
        }

        // testing boolean
        if (bool){
            System.out.println("Boolean is true");
        } else {
            System.out.println("Boolean is false");
        }

        // testing double
        if (decimal < 1 && decimal > 0) {
            System.out.println(decimal + " is a positive number less than 1");
        } else if (decimal < 0) {
            System.out.println(decimal + " is a negative number");
        } else if (decimal > 1) {
            System.out.println(decimal + " is greater than 1");
        } else {
            System.out.println("Error with value " + decimal);
        }
        
        // testing String
        if (mystring.contains("h")){
            System.out.println(mystring + " contains an 'h'");
        } else {
            System.out.println(mystring + "does not contain an 'h'");
        }

        // for loop
        System.out.println("\nPrinting 'Hi' until length of " + mystring +" is reached");
        for (int i=0; i<mystring.length(); i++) {
            System.out.println("Hi");
        }

        // while loop
        System.out.println("\nAdding 5 to " + decimal +" until it is greater than " + number);
        while(decimal < number) {
            System.out.println("Value of " + decimal +" is less than value of " + number);
            decimal = decimal + 5;
        }
        System.out.println(decimal +" is greater than " + number);
    }
 }