package softwaresystems.SpaceExploration;
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