// Description: Test the Stack and Queue classes

public class TestStructures {
    public static void main(String[] args) {
        Stack myStack = new Stack();
        Queue myQueue = new Queue();

        // push 5 elements to stack
        myStack.push(1);
        myStack.push(2);
        myStack.push(3);
        myStack.push(4);
        myStack.push(5);
        // pop until empty
        while (!myStack.isEmpty()) {
            System.out.println(myStack.pop());
        }
        // push 3 more elements
        myStack.push(6);
        myStack.push(7);
        myStack.push(8);

        // enqueue stack elements to queue
        while (!myStack.isEmpty()) {
            myQueue.enqueue(myStack.pop());        
        }
        // first element that goes into queue will be 8, then 7, then 6 so it should print in order 8, 7, 6

        // dequeue until queue is empty
        while (!myQueue.isEmpty()) {
            System.out.println(myQueue.dequeue());
        }

        // pop from empty stack and dequeue empty queue
        System.out.println(myStack.pop());
        System.out.println(myQueue.dequeue());

    }

}