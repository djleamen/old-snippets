package softwaresystems.SpaceExploration;
import java.util.ArrayList;

public class MissionControl {
    private static MissionControl instance;
    private final ArrayList<String> operationalLogs;
    public boolean operational;

    private MissionControl() {
        operational = false;
        operationalLogs = new ArrayList<>();
    }

    // Singleton instance
    public static MissionControl getInstance() {
        if (instance == null) {
            instance = new MissionControl();
        }
        return instance;
    }

    // Start and end operations
    public void startOperations() {
        if (!operational) {
            operationalLogs.add("Initializing Mission Control...");
            operationalLogs.add("All systems are online and functioning properly.");
            operational = true;
        } else {
            operationalLogs.add("Mission Control is already operational.");
        }
    }
    public void endOperations() {
        if (operational) {
            operationalLogs.add("Shutting down Mission Control...");
            operationalLogs.add("Mission Control shutdown complete.");
            operational = false;
        } else {
            operationalLogs.add("Mission Control is already not operational.");
        }
    }

    public ArrayList<String> getOperationalLogs() {
        return operationalLogs;
    }
}