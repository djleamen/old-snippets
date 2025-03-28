/**
 * An Element in a linked list, used in Stack and Queue implementations.
 */
public class Element {
    private final Object element;
    private Element next;
    private Element prev;

    /**
     * Constructs an Element with the specified object.
     *
     * @param element the object contained in the element
     */
    public Element(Object element) {
        this.element = element;
        this.next = null;
        this.prev = null;
    }

    /**
     * Returns the object contained in this element.
     *
     * @return the object contained in this element
     */
    public Object getElement() {
        return element;
    }


    /**
     * Returns the next element in the list.
     *
     * @return the next element in the list
     */
    public Element getNext() {
        return next;
    }

    /**
     * Sets the next element in the list.
     *
     * @param next the next element to set
     */
    public void setNext(Element next) {
        this.next = next;
    }

    /**
     * Returns the previous element in the list.
     *
     * @return the previous element in the list
     */
    public Element getPrev() {
        return prev;
    }

    /**
     * Sets the previous element in the list.
     *
     * @param prev the previous element to set
     */
    public void setPrev(Element prev) {
        this.prev = prev;
    }
}
