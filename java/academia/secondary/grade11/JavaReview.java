// Description: This program converts Roman Numerals to Integers. It asks for user input and then converts the input to an integer value. It also checks for invalid characters and returns an error message if any are entered.
import java.util.*;   

public class JavaReview
{   
  public static int value(char letter)   
  {   
      // convert character entered to associated value
    if (letter == 'I')   
      return 1;   
    if (letter == 'V')
      return 5;   
    if (letter == 'X')   
      return 10;   
    if (letter == 'L')   
      return 50;   
    if (letter == 'C')   
      return 100;   
    if (letter == 'D')   
      return 500;   
    if (letter == 'M')   
      return 1000;   
    return 0;
    // return 0 in case of invalid input
  }
  // method to later call back to to apply input
  public static int convertToDec(String num)   
  {   
    int total = 0;   
    for (int i=0; i<num.length(); i++)
        // for loop to add numerals mathematically
    {   
      int num1 = value(num.charAt(i));   
      if (i+1 <num.length())   
      {   
        int num2 = value(num.charAt(i+1));      
        if (num1 >= num2)   
        {   
          total = total + num1;   
        }   
        else  
        {   
          total = total - num1;   
        }   
      }   
      else  
      {   
        total = total + num1;   
      }   
    }   
    return total;   
  }
  
  // main method, ask for input 
  public static void main(String args[])   
  {   
    try (Scanner scan = new Scanner (System.in)) {
      System.out.println("Please enter a Roman Numeral: ");
      String input = scan.nextLine();  
      int answer = convertToDec(input);
      // invalid if method returns with 0 or if any invalid letter is entered
      if (answer == 0 || input.contains("A") || input.contains("B") || input.contains("E") || input.contains("F") || input.contains("G") || input.contains("H") || input.contains("J") || input.contains("K") || input.contains("N") || input.contains("O") || input.contains("P")|| input.contains("Q") || input.contains("R") || input.contains("S") || input.contains("T") || input.contains("U") || input.contains("W") || input.contains("Y") || input.contains("Z")) {
      System.out.println("Invalid character entered.");
      } else {
          System.out.println("The corresponding Integer value is: "+answer);
}
    }
    }
  }
