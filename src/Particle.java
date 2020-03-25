import java.util.ArrayList;

public class Particle {

    private ArrayList<Location> locations;
    private Location startingLocation;

    public Particle(Location firstLocation){
        locations = new ArrayList<>();
        locations.add(firstLocation);
        startingLocation = firstLocation;
    }

    public ArrayList<Location> getLocations() {
        return locations;
    }

    public void addLocation(Location loc){
        locations.add(loc);
    }

    public Location getStartingLocation() {
        return startingLocation;
    }
}
