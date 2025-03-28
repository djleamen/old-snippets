package softwaresystems.SpaceExploration;
import softwaresystems.SpaceExploration.Mission;
import softwaresystems.SpaceExploration.MissionCollection;
import softwaresystems.SpaceExploration.MissionControl;

public class SpaceFacade {
    private final MissionControl missionControl;
    private final MissionCollection missionCollection;

    public SpaceFacade() {
        this.missionControl = MissionControl.getInstance();
        this.missionCollection = new MissionCollection();
    }

    // Initialize command center
    public void initializeCommandCenter() {
        missionControl.startOperations();
    }

    // Schedule mission
    public void scheduleMission(String planet, String experiment) {
        missionCollection.addMission(new Mission(planet, experiment));
    }

    // List all missions
    public void listMissions() {
        for (Mission mission : missionCollection) {
            System.out.println(mission.getDetails());
        }
    }

    // Shutdown command center
    public void shutdownCommandCenter() {
        missionControl.endOperations();
    }
}
