// Define a class SinglyLinkedList that implements a singly linked list.

public class SinglyLinkedList<E> {

    private static class Node<E> {
        E value;
        Node<E> next;

        Node(E value) {
            this.value = value;
            this.next = null;
        }

        E getValue() {
            return value;
        }
    }

    Node<E> head;
    int size;

    public SinglyLinkedList() {
      this.head = null;
      size = 0;
    }

    // go through until you've found end of the list and add val
    public void add(E val){
      Node<E> add = new Node<>(val);
      // base case
      if (size == 0) {
        this.head = add;
      } else {
        Node<E> temp = this.head;
        while (temp.next != null) {
          temp = temp.next;
        }
        temp.next = add;
      }
      size ++;
    }

    public E get(int ind){
      int temp = 0;
      if (ind > this.size-1 || this.size == 0) {
        return null;
      } else if (ind == 0) {
        return this.head.value;
      } else {
        Node<E> ptr = this.head;
        while (temp < ind && ptr.next != null) {
          temp++;
          ptr = ptr.next;
        }
        return ptr.getValue();
      }
    }

    public void set(int ind, E val){
      Node<E> temp = this.head;
      for (int i = 0; i < ind; i++) {
        temp = temp.next;
      }
      temp.value = val;
    }

    public E remove(int ind){
       E deletedVal;
       if (ind == 0) {
        deletedVal = this.head.value;
        this.head = this.head.next;
    } else {
        Node<E> temp = this.head;
        for (int i = 0; i < ind - 1; i++) {
            temp = temp.next;
        }
        deletedVal = temp.next.value;
        temp.next = temp.next.next;
    }
    size--;
    return deletedVal;
}

    public int size(){
      return size;
    }

    public boolean contains(E val){
      Node<E> temp = this.head;
      while (temp != null) {
        // using .equals() for actual vals
        if (temp.value.equals(val)) {
          return true;
        }
        temp = temp.next;
      }
      return false;
    }

    public int indexOf(E val){
      Node<E> temp = this.head;
      int ind = 0;
      while (temp != null) {
        if (temp.value.equals(val)) {
          return ind;
        }
        temp = temp.next;
        ind++;
      }
      return -1;
    }

    public void clear(){
      this.head = null;
      this.size = 0;
    }

    @Override
    public String toString() {
      String result = "[";
      if (this.size != 0) {
        Node<E> temp = this.head;
        while (temp.next != null) {
          result += temp.value + ", ";
          temp = temp.next;
        }
        result += temp.value;
      }
      result += "]";
      return result;
    }

    public static void main (String [] args) {
      SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
      // test add
      list.add(1);
      list.add(200);
      list.add(3);
      // test tostring
      System.out.println(list);
      // test set
      list.set(1, 2);
      list.add(400);
      System.out.println(list);
      // test size
      System.out.println("Size: " + list.size());
      // test get
      System.out.println("Index 2: " + list.get(2));
      // get index edge cases
      System.out.println("Index 0: " + list.get(0));
      System.out.println("Index 100: " + list.get(100));
      // test remove
      list.remove(3);
      System.out.println(list);
      // test indexOf
      System.out.println("Where's 3?: " + list.indexOf(3));
      System.out.println("Where's 10?: " + list.indexOf(10));
      // test contains
      System.out.println("Does list contain 1? " + list.contains(1));
      System.out.println("Does list contain 230? " + list.contains(230));
    }
}