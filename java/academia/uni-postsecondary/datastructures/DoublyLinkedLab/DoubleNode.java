// Description: This class is a generic class that represents a node in a doubly linked list.

public class DoubleNode<E> {
    E value;
    DoubleNode<E> next;
    DoubleNode<E> prev;

    // constructor of empty node
    public DoubleNode(E val) {
        this.value = val;
        this.next = null;
        this.prev = null;
    }

    // get current value of node
    public E getValue(){
        return value;
    }

    // get the next node
    public DoubleNode<E> getNext(){
        return next;
    }

    // get the previous node
    public DoubleNode<E> getPrev() {
        return prev;
    }

    // set/change the current node's value
    public void setValue(E val){
        value = val;
    }

    // set/change the next node's value
    public void setNext(DoubleNode<E> next){
        this.next = next;
    }

    // set/change the previous node's value
    public void setPrev(DoubleNode<E> prev) {
        this.prev = prev;
    }
}