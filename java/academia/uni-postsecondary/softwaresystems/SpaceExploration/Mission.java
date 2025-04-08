/*
 * Description: This class represents a mission in a space exploration context.
 * It contains details about the world and the experiment being conducted.
 * The class provides a constructor to initialize these details and a method to
 * retrieve them in a formatted string.
 */

public class Mission {
    private final String world;
    private final String experiment;

    public Mission(String world, String experiment) {
        this.world = world;
        this.experiment = experiment;
    }

    public String getDetails() {
        return "World: " + world + " | Experiment: " + experiment;
    }
}