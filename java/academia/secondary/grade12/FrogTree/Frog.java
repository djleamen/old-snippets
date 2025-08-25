public class Frog {

    private String name;
    private String colour;

    public Frog() {
        this.name = "Frog";
        this.colour = "green";
    }

    public Frog(String name, String colour) {
        this.name = name;
        this.colour = colour;
    }

    public String getColour() {
        return colour;
    }

    public String getName() {
        return name;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name + " the " + colour + " frog";
    }

}