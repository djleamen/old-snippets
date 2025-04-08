// Description: This program demonstrates the use of a Queue in Java.

import java.util.LinkedList;
import java.util.Queue;

public class JavaQueue {

    public static void main(String[] args) {
        Queue<Character> myQueue = new LinkedList<>();
        System.out.println("Is the Queue empty? " + myQueue.isEmpty());
        String randomStr = "supercalifragilisticexpialidocious";
        System.out.println("Adding '" + randomStr + "'...");
        for (int i = 0; i<randomStr.length(); i++) {
            Character currentChar = randomStr.charAt(i);
            myQueue.add(currentChar);
        }
        System.out.println("Current size of Queue is " + myQueue.size());
        System.out.println("Is the Queue empty? " + myQueue.isEmpty());
        System.out.println("Front value is: " + myQueue.peek());

        myQueue.remove();
        myQueue.remove();
        myQueue.remove();
        System.out.println("Size after removing three characters: " + myQueue.size());
        System.out.println("Front value is: " + myQueue.peek());

        while(!myQueue.isEmpty()) {
            myQueue.remove();
        }
        System.out.println("The size after emptying the queue is: " + myQueue.size());

    }
}