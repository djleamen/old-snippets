// Description: A program that calculates the factorial of a number.

public class Factorial {
    public static int factorial(int number) {
        if (number < 0) {
            System.out.println("Invalid");
        }
        int result = 1;
        for (int i = 2; i <= number; i++) {
            result *= i;
        }
        return result;
    }
    public static void main(String[] args) {
        int number = 10;
        System.out.println(factorial(number));
    }
}