package datastructures.TowersOfHanoi;

/**
 * Represents a disc in the Towers of Hanoi problem.
 * Each disc has a width and may have another disc below it.
 */
public class Disc {
    private final int width;
    private Disc below;

    /**
     * Constructs a new Disc with a given width.
     *
     * @param width the width of the disc
     * @throws IllegalArgumentException if the width is less than or equal to zero
     */
    public Disc(int width) {
        if (width <= 0) {
            throw new IllegalArgumentException("Width must be greater than zero.");
        }
        this.width = width;
        this.below = null;
    }

    /**
     * Gets the width of the disc.
     *
     * @return the width of the disc
     */
    public int getWidth() {
        return width;
    }

    /**
     * Gets the disc below the current disc.
     *
     * @return the disc below, or null if there is no disc below
     */
    public Disc getBelow() {
        return below;
    }

    /**
     * Sets the disc below the current disc.
     *
     * @param below the disc to set below
     */
    public void setBelow(Disc below) {
        this.below = below;
    }
}
