// Description: This program demonstrates the implementation of a stack data structure in C++.

#include <iostream>
using namespace std;

class Stack {
    private:
    int size;
    int *stack;
    int top;

    public:
    Stack(int s) {
        size = s;
        stack = new int[size];
        top = -1;
    }

    ~Stack() {
        delete[] stack;
    }

    bool isEmpty() {
        return top == -1;
    }

    bool isFull() {
        return top == size - 1;
    }

    void push(int n) {
        if (!isFull()) {
            stack[++top] = n;
        } else {
            cout << "Stack is full!" << endl;
        }
    }

    int pop() {
        if (!isEmpty()) {
            return stack[top--];
        } else {
            cout << "Stack is empty!" << endl;
            return -1; // Returning -1 as an error value
        }
    }

    void display() {
        if (isEmpty()) {
            cout << "Stack is empty!" << endl;
            return;
        }

        for (int i = top; i >= 0; i--) {
            cout << stack[i] << " ";
        }
        cout << endl;
    }

    int getTopElement() {
        if (!isEmpty()) {
            return stack[top];
        } else {
            cout << "Stack is empty!" << endl;
            return -1; // Returning -1 as an error value
        }
    }
};
        
int main() {
    Stack stack(3);

    stack.push(10);
    stack.push(20);
    stack.push(30);

    stack.display(); // 30 20 10

    cout << "Top element: " << stack.getTopElement() << endl;

    return 0;
}
