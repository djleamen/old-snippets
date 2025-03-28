package datastructures;
// Description: This program contains three methods that manipulate strings. 
// The first method reverses a string, the second method checks if a string is a palindrome, 
// and the third method counts the number of occurrences of a character in a string. 

import java.util.Scanner;

public class Strings {
    public static Scanner scan = new Scanner(System.in);

    public static String reverse(String str) {
        if (str == null || str.length() <= 1) {
            return str;
        }
        return reverse(str.substring(1)) + str.charAt(0);
    }

    public static boolean isPalindrome(String str) {
        if (str == null || str.length() <= 1) {
            return true;
        }
        if (str.charAt(0) != str.charAt(str.length() - 1)) {
            return false;
        }
        return isPalindrome(str.substring(1, str.length() - 1));
    }

    public static int count(String str, char ch) {
        if (str.isEmpty()) {
            return 0;
        }
        return (str.charAt(0) == ch ? 1 : 0) + count(str.substring(1), ch);
    }
    public static void main(String[] args) {
        int option = 0;
        String str = "";
        char ch;
        while (option!= -1) {
            System.out.println("Which problem would you like to try?");
            System.out.println("1. Reverse a string");
            System.out.println("2. Check if string is a palindrome");
            System.out.println("3. Count character in string");
            System.out.println("4. Quit the words game");
            option = scan.nextInt();
            switch (option) {
                case 1:
                    System.out.println("Enter a string to reverse: ");
                    str = scan.next();
                    System.out.println(str + " reversed is " + "'" + reverse(str) + "'");
                    break;
                case 2:
                    System.out.println("Enter a string to check if palindrome: ");
                    str = scan.next();
                    if (!isPalindrome(str)) {
                        System.out.println(str + " is not a palindrome.");
                    } else {
                        System.out.println(str + " is a palindrome!");
                    }
                    break;
                case 3:
                    System.out.println("Enter a word: ");
                    str = scan.next();
                    System.out.println("Enter a character to count its occurences in your word: ");
                    ch = scan.next().charAt(0);
                    System.out.println("'" + ch + "' appears in " + str + " " + count(str, ch) + " times!");
                    break;
                case 4:
                    System.out.println("Bye bye!");
                    option = -1;
                    break;
                    
              }   
    
           }
     }
}
