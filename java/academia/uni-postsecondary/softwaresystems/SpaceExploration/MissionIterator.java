/*
 * Description:
 * This class implements an iterator for a collection of missions.
 * It allows for sequential access to the missions in the collection.
 */

import java.util.Iterator;
import java.util.ArrayList;

public class MissionIterator implements Iterator<Mission> {
    private final ArrayList<Mission> missions;
    private int position = 0;

    public MissionIterator(ArrayList<Mission> missions) {
        this.missions = missions;
    }

    // Next missions
    @Override public boolean hasNext() {
        return position < missions.size();
    }
    @Override public Mission next() {
        if (hasNext()) {
            return missions.get(position++);
        } else {
            throw new IllegalStateException("No more missions available.");
        }
    }
}
