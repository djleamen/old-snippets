package datastructures.DoublyLinkedLab;
// Description: Test class for DoublyLinkedList

public class ListTest {
    public static void main(String[] args) {
        DoublyLinkedList list = new DoublyLinkedList();
        
        // add value to front test
        list.addToFront(2);
        list.addToFront(1);
        list.addToFront(0);
        System.out.println(list); 

        // add value to end test
        list.addToEnd(3);
        list.addToEnd(4);
        System.out.println(list); 

        // remove from front test
        list.removeFromFront();
        System.out.println(list); 

        // remove from end test
        list.removeFromEnd();
        System.out.println(list);

        // test index of
        System.out.println("Index of 2: " + list.indexOf(2));
        System.out.println("Index of 10: " + list.indexOf(10)); 

        // test length
        System.out.println("Length: " + list.getLength());
        
    }
}