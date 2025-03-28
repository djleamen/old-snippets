/**
 * A queue implementation using a linked list of Elements.
 */
public class Queue {
    private Element front;
    private Element back;
    private int size;

    /**
     * Constructs an empty Queue.
     */
    public Queue() {
        this.front = null;
        this.back = null;
        this.size = 0;
    }

    /**
     * Returns the number of elements in the queue.
     *
     * @return the size of the queue
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Checks if the queue is empty.
     *
     * @return true if the queue is empty, false otherwise
     */
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * Adds an Element to the end of the queue.
     *
     * @param element the Element to add to the queue
     */
    public void enqueue(Element element) {
        if (element != null) {
            if (this.size > 0) {
                this.back.setPrev(element);
                element.setNext(this.back);
            } else {
                this.front = element;
            }
            this.back = element;
            this.size++;
        }
    }

    /**
     * Creates an Element from an Object and adds it to the end of the queue.
     *
     * @param object the object to add to the queue
     */
    public void enqueue(Object object) {
        if (object != null) {
            enqueue(new Element(object));
        }
    }

    /**
     * Returns the front element without removing it from the queue.
     *
     * @return the front element of the queue, or null if the queue is empty
     */
    public Object front() {
        return isEmpty() ? null : this.front.getElement();
    }

    /**
     * Removes and returns the front element from the queue.
     *
     * @return the front element of the queue, or null if the queue is empty
     */
    public Object dequeue() {
        if (isEmpty()) {
            return null;
        }

        Object value = this.front.getElement();
        this.front = this.front.getPrev();

        if (this.front != null) {
            this.front.setNext(null);
        } else {
            this.back = null;
        }

        this.size--;
        return value;
    }
}
