package datastructures.TowersOfHanoi;

/**
 * Represents a tower in the Towers of Hanoi problem.
 * Each tower has a name and can hold a stack of discs.
 */
public class Tower {
    private Disc top;
    private final String name;
    private int size;

    /**
     * Constructs a new Tower with a given name.
     *
     * @param name the name of the tower
     * @throws IllegalArgumentException if the name is null or empty
     */
    public Tower(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Tower name cannot be null or empty.");
        }
        this.top = null;
        this.name = name;
        this.size = 0;
    }

    /**
     * Gets the name of the tower.
     *
     * @return the name of the tower
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the top disc of the tower.
     *
     * @return the top disc, or null if the tower is empty
     */
    public Disc getTop() {
        return top;
    }

    /**
     * Gets the current size of the tower.
     *
     * @return the number of discs in the tower
     */
    public int getSize() {
        return size;
    }

    /**
     * Sets the top disc of the tower.
     *
     * @param top the disc to set as the top disc
     */
    public void setTop(Disc top) {
        this.top = top;
    }

    /**
     * Pushes a disc onto the tower.
     *
     * @param disc the disc to push
     * @return true if the push was successful, false otherwise
     * @throws IllegalArgumentException if the disc is null
     */
    public boolean push(Disc disc) {
        if (disc == null) {
            throw new IllegalArgumentException("Disc cannot be null.");
        }
        if (top == null || disc.getWidth() < top.getWidth()) {
            disc.setBelow(this.top);
            top = disc;
            size++;
            return true;
        } else {
            System.out.println("Error: Cannot place a larger disc on top of a smaller disc.");
            return false;
        }
    }

    /**
     * Pops the top disc from the tower.
     *
     * @return the removed top disc, or null if the tower is empty
     */
    public Disc pop() {
        if (top == null) {
            return null;
        }
        Disc temp = top;
        top = top.getBelow();
        temp.setBelow(null);
        size--;
        return temp;
    }
}
