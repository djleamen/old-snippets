// Description: This class represents a location on a campus map.

import java.util.Objects;

/**
 * Represents a location on a campus map.
 */
public class Location {


    private final String name;
    private final String code;
    private final Access access;

    /**
     * Constructs a new Location object with the given name, code, and access level.
     *
     * @param name  the name of the location
     * @param code  the code of the location
     * @param access the access level of the location
     */
    public Location(String name, String code, Access access) {
        this.name = name;
        this.code = code;
        this.access = access;
    }

    /**
     * Gets the name of the location.
     *
     * @return the name of the location
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the access status of the location.
     *
     * @return the access status of the location
     */
    public Access getAccess() {
        return access;
    }

    /**
     * Gets the code of the location.
     *
     * @return the code of the location
     */
    public String getCode() {
        return code;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param o the reference object with which to compare
     * @return {@code true} if this object is the same as the obj
     * argument; {@code false} otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Objects.equals(name, location.name);
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return a hash code value for this object
     */
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    /**
     * Returns a string representation of the object.
     *
     * @return a string representation of the object
     */
    @Override
    public String toString() {
        return name + " (" + code + ")";
    }
}
