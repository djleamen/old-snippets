// Description: DoublyLinkedList class that contains a head and tail node, and a length variable to keep track of the list. 

public class DoublyLinkedList<E> {
    DoubleNode<E> head;
    DoubleNode<E> tail;
    int length;

    // constructor of empty list
    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.length = 0;
    }

    // get length method
    public int getLength() {
        return length;
    }

    // add a new node containing value to front, if list is empty it becomes the entire list
    public void addToFront(E value) {
        DoubleNode<E> newNode = new DoubleNode<E>(value);
        if (head == null) { 
            head = newNode;
            tail = newNode;
        } else {
            newNode.setNext(head);
            head.setPrev(newNode);
            head = newNode;
        }
        length++;
    }

    // remove the value at the head node by setting the second element to the new head, if the list only contains one element then clear the whole list
    public void removeFromFront() {
        // edge case if list is empty
        if (head == null) { 
            System.out.println("Nothing to remove");
        }
        if (head == tail) { 
            head = null;
            tail = null;
        } else {
            head = head.getNext();
            head.setPrev(null);
        }
        length--;
    }

    // add a new node containing value to the end of the list, if list is empty it becomes the entire list
    public void addToEnd(E value) {
        DoubleNode<E> newNode = new DoubleNode<E>(value);
        if (tail == null) { 
            head = newNode;
            tail = newNode;
        } else {
            newNode.setPrev(tail);
            tail.setNext(newNode);
            tail = newNode;
        }
        length++;
    }

    // remove the value at the tail node by setting the previous element to the new tail, if the list only contains one element then clear the whole list
    public void removeFromEnd() {
        // edge case if list is empty
        if (tail == null) { 
            System.out.println("Nothing to remove");
        }
        if (head == tail) { 
            head = null;
            tail = null;
        } else {
            tail = tail.getPrev();
            tail.setNext(null);
        }
        length--;
    }

    // chosen method inspired by LinkedList javadoc ... find where a value is located in index using loop, return -1 if it dne
    public int indexOf(E value) {
        DoubleNode<E> current = head;
        int index = 0;
        while (current != null) {
            if (current.getValue() == value) {
                return index;
            }
            current = current.getNext();
            index++;
        }
        return -1;
    }

    // toString() method to format list as [x, y, z]
    public String toString() {
        String result = "[";
        DoubleNode current = head;
        while (current != null) {
            result += current.getValue();
            if (current.getNext() != null) {
                result += ", ";
            }
            current = current.getNext();
        }
        result += "]";
        return result;
    }
}