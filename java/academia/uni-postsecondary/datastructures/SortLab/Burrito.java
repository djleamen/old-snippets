package datastructures.SortLab;
/**
 * This class represents a Burrito with various attributes and methods to manipulate them.
 */
public class Burrito {
    // Attributes
    private String size;
    private String protein;
    private String rice;
    private String beans;
    private String toppings;
    private boolean guac;

    /**
     * Default constructor, initializes a small burrito with no fillings.
     */
    public Burrito() {
        this.size = "small";
        this.protein = "none";
        this.rice = "none";
        this.beans = "none";
        this.toppings = "";
        this.guac = false;
    }

    /**
     * Constructor with size and protein.
     *
     * @param size    the size of the burrito
     * @param protein the type of protein
     */
    public Burrito(String size, String protein) {
        this.size = size;
        this.protein = protein;
        this.rice = "none";
        this.beans = "none";
        this.toppings = "";
        this.guac = false;
    }

    /**
     * Full constructor with all attributes.
     *
     * @param size     the size of the burrito
     * @param protein  the type of protein
     * @param rice     the type of rice
     * @param beans    the type of beans
     * @param toppings additional toppings
     * @param guac     whether the burrito has guacamole
     */
    public Burrito(String size, String protein, String rice, String beans, String toppings, boolean guac) {
        this.size = size;
        this.protein = protein;
        this.rice = rice;
        this.beans = beans;
        this.toppings = toppings;
        this.guac = guac;
    }

    /**
     * Gets the size of the burrito.
     *
     * @return the size of the burrito
     */
    public String getSize() {
        return size;
    }

    /**
     * Sets the size of the burrito.
     *
     * @param size the new size of the burrito
     */
    public void setSize(String size) {
        this.size = size;
    }

    /**
     * Gets the protein of the burrito.
     *
     * @return the protein of the burrito
     */
    public String getProtein() {
        return protein;
    }

    /**
     * Sets the protein of the burrito.
     *
     * @param protein the new protein of the burrito
     */
    public void setProtein(String protein) {
        this.protein = protein;
    }

    /**
     * Gets the type of rice in the burrito.
     *
     * @return the type of rice in the burrito
     */
    public String getRice() {
        return rice;
    }

    /**
     * Sets the type of rice in the burrito.
     *
     * @param rice the new type of rice in the burrito
     */
    public void setRice(String rice) {
        this.rice = rice;
    }

    /**
     * Gets the type of beans in the burrito.
     *
     * @return the type of beans in the burrito
     */
    public String getBeans() {
        return beans;
    }

    /**
     * Sets the type of beans in the burrito.
     *
     * @param beans the new type of beans in the burrito
     */
    public void setBeans(String beans) {
        this.beans = beans;
    }

    /**
     * Gets the additional toppings of the burrito.
     *
     * @return the additional toppings of the burrito
     */
    public String getToppings() {
        return toppings;
    }

    /**
     * Sets the additional toppings of the burrito.
     *
     * @param toppings the new additional toppings of the burrito
     */
    public void setToppings(String toppings) {
        this.toppings = toppings;
    }

    /**
     * Checks if the burrito has guacamole.
     *
     * @return true if the burrito has guacamole, false otherwise
     */
    public boolean isGuac() {
        return guac;
    }

    /**
     * Sets whether the burrito has guacamole.
     *
     * @param guac true if the burrito has guacamole, false otherwise
     */
    public void setGuac(boolean guac) {
        this.guac = guac;
    }

    /**
     * Calculates the cost of the burrito based on its attributes.
     *
     * @return the cost of the burrito
     */
    public double getCost() {
        double cost = size.equals("small") ? 5.00 : 7.50;
        switch (protein) {
            case "chicken":
            case "steak":
                cost += 2.00;
                break;
            case "carnitas":
            case "sofritas":
                cost += 3.00;
                break;
        }
        if (!rice.equals("none")) cost += 1.00;
        if (!beans.equals("none")) cost += 1.00;
        if (guac) cost += 2.00;
        return cost;
    }

    /**
     * Returns a string representation of the burrito.
     *
     * @return a string representation of the burrito
     */
    @Override
    public String toString() {
        String guacString = guac ? "and guacamole" : "and no guacamole";
        return String.format("%s %s Burrito with %s rice, %s beans, %s %s. Cost: $%.2f",
                size, protein, rice, beans, toppings, guacString, getCost());
    }
}