/**
 * A stack implementation using a linked list of Elements.
 */
public class Stack {
    private Element top;
    private int size;

    /**
     * Constructs an empty Stack.
     */
    public Stack() {
        this.top = null;
        this.size = 0;
    }

    /**
     * Constructs a Stack with an initial object.
     *
     * @param obj the object to initialize the stack with
     */
    public Stack(Object obj) {
        this();
        push(obj);
    }

    /**
     * Pushes an Element onto the stack.
     *
     * @param element the Element to push onto the stack
     */
    public void push(Element element) {
        if (element != null) {
            element.setNext(this.top);
            this.top = element;
            this.size++;
        }
    }

    /**
     * Creates an Element from an Object and pushes it onto the stack.
     *
     * @param object the object to push onto the stack
     */
    public void push(Object object) {
        if (object != null) {
            push(new Element(object));
        }
    }

    /**
     * Removes and returns the top element from the stack.
     *
     * @return the top element of the stack, or null if the stack is empty
     */
    public Object pop() {
        if (!isEmpty()) {
            Object obj = this.top.getElement();
            this.top = this.top.getNext();
            this.size--;
            return obj;
        } else {
            return null;  // Return null if stack is empty
        }
    }

    /**
     * Returns the top element without removing it from the stack.
     *
     * @return the top element of the stack, or null if the stack is empty
     */
    public Object peek() {
        return !isEmpty() ? this.top.getElement() : null;
    }

    /**
     * Checks if the stack is empty.
     *
     * @return true if the stack is empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the number of elements in the stack.
     *
     * @return the size of the stack
     */
    public int getSize() {
        return size;
    }
}
