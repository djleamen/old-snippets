package datastructures.Stack;
// Define a class called Stack that implements a stack data structure.

public class Stack<E> {
    private Node<E> top;
    private int size;

    public Stack() {
        this.top = null;
        this.size = 0;
    }

    public void push(E val) {
        Node<E> add = new Node<>(val);
        if(!empty()) {
            add.next = this.top;
        }
        this.top = add;      
        size++;
    }

    public E pop() {
        if (empty()) {
            return null;
        } else {
            E deletedVal = this.top.value;
            this.top = this.top.next; 
            size--;
            return deletedVal;
        }
    }

    public E peek() {
        if (empty()) {
            return null;
        }
        return this.top.value;
    }

    public int size() {
        return this.size;
    }

    public boolean empty() {
        return this.size == 0;
    }

    private class Node<E> {
        E value;
        Node<E> next;

        Node(E value) {
            this.value = value;
            this.next = null;
        }
    }
}