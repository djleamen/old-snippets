// Description: This program contains 5 methods that each answer a question from the assignment.

import java.util.Scanner;
// DJ Leamen, April 5, 2022


public class ForLoops {
// Question 1: Asks user for a number between 1 and 100, prints all multiples of this number below 10,000
// import Scanner from utility library​
    public static void q1() {
        int i;
        int n = 10000;
        int num;
        // request number input and save under int variable num
        System.out.println("Enter a number between 1 and 100: ");
        try (Scanner scan = new Scanner (System.in)) {
            num = scan.nextInt();
        }
        // make sure number is within range (1-100)
        if (num < 0 || num > 100) {
            System.out.println("Invalid input.");
        } else {
            // loop printing of multiples until var num (10000) is reached
            for(i = 1 ; i <= n ; i++) {
                if (i % num == 0) {
                    System.out.println(+ i );
                }
            }
        }
    }

    public static void q2() {
        // Asks the user for a number below 100 and prints the factorial of this number.
        // create variables for loop parameters
        int i;
        int factorial = 1;
        int num;
               // request number input and save under int variable num
        System.out.println("Enter a number between 1 and 100: ");
        try (Scanner scan = new Scanner (System.in)) {
            num = scan.nextInt();
        }
        // make sure number is within range (1-100)
        if (num < 0 || num > 100) {
            System.out.println("Invalid input."); 
        } else {
            // loop calculation of var factorial until var i is equal to entered num
            for(i = 1; i <= num; i++) {
                   factorial = factorial * i;
               }
            // print result to user
            System.out.println("The factorial of " + num + " is: "+ factorial);
        }
    }

    public static void q3() {
        int i;
       // a and b to check values
       int a;
       int b;
       // r to hold remaining
       int r;
       // for loop to calculate between range 0 and 909 (100th palindromic number)
       for (i = 0; i <= 909; i++) {
           a = i;
           b = 0;
           // while loop to calculate palindromic numbers
           while (a != 0) {
               r = a % 10;
               a /= 10;
               b = b * 10 + r;
           }
           // print i result if it equals b calculation with space between value
           if (i == b) {
               System.out.print(i + " ");
               // if statement to separate lines every 10th palindromic value
               if (i == 9 || i == 101 || i == 202 || i == 303 || i == 404 || i == 505 || i == 606 || i == 707 || i == 808 || i == 909) {
                   System.out.print("\n");
               }
           }
       }
    }

    public static void q4() {
        // create variables for loop parameters
        int i;
        int num;
       // request number input and save under int variable num
       System.out.println("Enter a number: ");
       try (Scanner scan = new Scanner (System.in)) {
        num = scan.nextInt();
    }
       // if statement to ensure num is over 6 and results can be returned to user
        if (num <= 6) {
            System.out.println("There are no multiples of 6 below this number.");
         } else {
            System.out.println("The multiples of 6 below this number are:");
        // for loop to decide if var i is a multiple of 6 until i <= num
        for(i = 1; i <= num; i++) {
                if (i % 6 == 0) {
                    // print multiples of 6
                    System.out.println(i);
                }
            }
        }
    }

    public static void q5() {
        // create parameters for loop
        int width;
        int height;
        int i = 0;
        int n;
        // request value inputs
        System.out.println("Enter a character: ");
        try (Scanner scan = new Scanner (System.in)) {
            String character = scan.nextLine();
            System.out.println("Enter a width: ");
            width = scan.nextInt();
            System.out.println("Enter a height: ");
            height = scan.nextInt();
            // for loop to print the character by line (height) until value n <= height
            for (n = 1,i=width; n <= height && i <= width; n++) {
                // print the character in-line (width) until value i representing width is <= width
                // minus 1 to account for first character placed by height
                System.out.println(character + character.repeat(i-1));
            }
        }
    }
    

    public static void main(String args[] ) {
     q1();
     q2();
     q3();
     q4();
     q5();
    }
}