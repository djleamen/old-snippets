package datastructures.Stack;
// This is a test class for the Stack class

public class StackTest {
    public static void main(String[] args) {
        Stack<Integer> myStack = new Stack<Integer>();
        myStack.push(4);
        myStack.push(5);
        myStack.push(6);
        System.out.println(myStack.size());
        System.out.println(myStack.peek());
        System.out.println(myStack.peek());
        while (!myStack.empty()) {
            System.out.println((myStack.pop()));
        }
    }
}