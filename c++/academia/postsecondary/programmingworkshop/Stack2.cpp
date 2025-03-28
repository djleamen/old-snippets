// Description: This program demonstrates the implementation of a stack using an array.

#include <iostream>
using namespace std;


class Stack {
    private:
    int size;
    int *stack;
    int top;
    
    public:
    Stack(int s) {
        //allocate space in heap
        size = s;
        stack = new int[size];
        top = -1;
    }

    bool isEmpty() {
        return top == -1;
    }

    bool isFull() {
        return top == size - 1;
    }

    void push(int n) {
        if(isFull()) {
            cout <<"Stack is full"<<endl;
            return;
        }
        stack[++top] = n;
    }

    int pop() {
        if(isEmpty()) {
            cout <<"Stack is empty"<<endl;
            return -1;
        }
        return stack[top--];
    }

    int getTopElement() {
        if(isEmpty()) {
            cout <<"Stack is empty"<<endl;
            return -1;
        }
        return stack[top];
        
    }

    void display() {
        for(int i = top; i >= 0; i--) {
            cout <<stack[i]<<" ";
        }
        cout <<endl;
    }


int main() {
    Stack stack = Stack(3);
    
    stack.push(10);
    stack.push(20);
    stack.push(30);
    
    stack.display(); // 30 20 10
    
    cout <<"Top element: "<<stack.getTopElement()<<endl;
       
    return 0;
    }
};