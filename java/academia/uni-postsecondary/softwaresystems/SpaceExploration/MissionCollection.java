package softwaresystems.SpaceExploration;
import java.util.ArrayList;
import java.util.Iterator;

public class MissionCollection implements Iterable<Mission> {
    private final ArrayList<Mission> missions = new ArrayList<>();

    public void addMission(Mission mission) {
        missions.add(mission);
    }

    @Override
    public Iterator<Mission> iterator() {
        return new MissionIterator(missions);
    }
}
