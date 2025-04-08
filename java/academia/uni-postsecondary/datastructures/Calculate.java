// Description: This program calculates the factorial of a number, the nth number in the 
// fibonacci sequence, and the power of a number.
import java.util.Scanner;

public class Calculate {
    public static Scanner scan = new Scanner(System.in);

    public static int factorial(int num) {
        if (num == 1) {
            return 1;
          } else {
            return num * factorial(num-1);
        }
    }

    public static int fibonacci(int num) {
        if (num == 0) {
            return 0;
        } else if (num == 1) {
            return 1;
        }
        return fibonacci(num - 1) + fibonacci(num - 2);
    }

    public static int power(int num, int pow) {
        if (pow == 0) {
            return 1;
        }
        return num * power(num, pow - 1);
    }

    public static void main(String[] args) {
        int option = 0;
        int num;
        int pow;
        while (option!= -1) {
            System.out.println("Which number problem would you like to use?");
            System.out.println("1. Calculate factorial");
            System.out.println("2. Calculate nth fibonacci");
            System.out.println("3. Calculate x^y");
            System.out.println("4. Quit the numbers game");
            option = scan.nextInt();
            switch (option) {
                case 1:
                    System.out.println("Enter a number to find its factorial: ");
                    num = scan.nextInt();
                    System.out.println("Factorial of " + num + " is " + factorial(num));
                    break;
                case 2:
                    System.out.println("Enter number in fibonacci sequence you would like to find: ");
                    num = scan.nextInt();
                    System.out.println("Index " + num + " in sequence of fibonacci is " + fibonacci(num));
                    break;
                case 3:
                    System.out.println("Enter a number for your base: ");
                    num = scan.nextInt();
                    System.out.println("Enter a number for your power: ");
                    pow = scan.nextInt();
                    System.out.println(num + " raised to the power of " + pow + " is " + power(num, pow));
                    break;
                case 4:
                    System.out.println("Bye bye!");
                    option = -1;
                    break;
                    
                }   
    
            }
        }
}