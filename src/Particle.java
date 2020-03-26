import java.util.ArrayList;

public class Particle {

    private ArrayList<Location> locations;
    private Location startingLocation;
    private Location currentLocation;

    public Particle(Location firstLocation){
        locations = new ArrayList<>();
        locations.add(firstLocation);
        startingLocation = firstLocation;
        currentLocation = firstLocation;
    }

    public ArrayList<Location> getLocations() {
        return locations;
    }

    public void addLocation(Location loc){
        currentLocation = loc;
        locations.add(loc);
    }

    public Location getStartingLocation() {
        return startingLocation;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }
}
