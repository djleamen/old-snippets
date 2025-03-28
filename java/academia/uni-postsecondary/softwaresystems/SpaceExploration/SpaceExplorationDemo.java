package softwaresystems.SpaceExploration;
import softwaresystems.SpaceExploration.SpaceFacade;

public class SpaceExplorationDemo {
    public static void main(String[] args) {
        // Initialize SpaceFacade
        SpaceFacade spaceFacade = new SpaceFacade();

        // Start command center
        spaceFacade.initializeCommandCenter();

        // Schedule missions
        spaceFacade.scheduleMission("Mars", "Search for water");
        spaceFacade.scheduleMission("Europa", "Study ice layers");
        spaceFacade.scheduleMission("Titan", "Analyze methane atmosphere");

        // List all missions
        spaceFacade.listMissions();

        // Shutdown command center
        spaceFacade.shutdownCommandCenter();
    }
}