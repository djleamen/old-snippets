// Description: This class is a representation of a burrito object.

class Burrito {
    String size;
    String protein;
    String rice;
    String beans;
    String toppings;
    boolean guac;
    double cost;

    Burrito() {
        this.size = "small";
        this.protein = "none";
        this.rice = "no";
        this.beans = "no";
        this.toppings = "";
        this.guac = false;
    }
    
    Burrito(String size, String protein) {
        this.size = size;
        this.protein = protein;
        this.rice = "no";
        this.beans = "no";
        this.toppings = "";
        this.guac = false;
    }

    Burrito(String size, String protein, String rice, String beans, String toppings, boolean guac) {
        this.size = size;
        this.protein = protein;
        this.rice = rice;
        this.beans = beans;
        this.toppings = toppings;
        this.guac = guac;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSize() {
        return this.size;
    }

    public void setProtein(String protein) {
        this.protein = protein;
    }

    public String getProtein() {
        return this.protein;
    }

    public void setRice(String rice) {
        this.rice = rice;
    }

    public String getRice() {
        return this.rice;
    }

    public void setBeans(String beans) {
        this.beans = beans;
    }
    
    public String getBeans() {
        return beans;
    }

    public void setToppings(String toppings) {
        this.toppings = toppings;
    }

    public String getToppings(){
        return toppings;
    }

    public void setGuac(boolean guac) {
        this.guac = guac;
    }

    public boolean getGuac() {
        return this.guac;
    }

    public double getCost() {
        if (this.size == "small") {
            this.cost = 5.00;
        } else if (this.size == "large"){
            this.cost = 7.50;
        }
        if (this.protein == "chicken" || this.protein == 
        "steak") {
            this.cost = this.cost + 2.00;
        } else if (this.protein == "carnitas" || this.protein == "sofritas") {
            this.cost = this.cost + 3.00;
        }
        if (this.rice != "no") { 
            this.cost = this.cost + 1.00;
        }
        if (this.beans != "no") { 
            this.cost = this.cost + 1.00;
        }
        if (this.guac) {
            this.cost = this.cost + 2.00;
        }
        return this.cost;
    }

    @Override
    public String toString() {
        return this.size + " " + this.protein + " Burrito with " + this.rice + " rice, " + this.beans + " beans, " + this.toppings + " and " + (this.guac ? "guacamole" : "no guacamole");
    }

    public static void main(String[] args) {}
}