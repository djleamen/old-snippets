/*
 * Description: This class represents a collection of missions.
 * It implements the Iterable interface to allow iteration over the missions.
 * The class provides a method to add missions and an iterator to traverse them.
 */

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
