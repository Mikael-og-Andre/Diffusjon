import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Location {

    //Coordinates
    private int xCord;
    private int yCord;

    public Location(int xCord, int yCord){
        this.xCord = xCord;
        this.yCord = yCord;
    }

    public int getXCord() {
        return xCord;
    }

    public int getYCord() {
        return yCord;
    }

    //returns cords of neighbour locations when edge counts as wall
    public static HashMap<Neighbours, Location> getNeighbourCordsWalled(Location loc,int gridLength, int gridHeight){
        int xCoordinate = loc.getXCord();
        int yCoordinate = loc.getYCord();

        HashMap<Neighbours,Location> neighbours = new HashMap<>();
        neighbours.put(Neighbours.LEFT_TOP,new Location(xCoordinate-1,yCoordinate-1));
        neighbours.put(Neighbours.LEFT_MID,new Location(xCoordinate-1,yCoordinate));
        neighbours.put(Neighbours.LEFT_BOT,new Location(xCoordinate-1,yCoordinate+1));
        neighbours.put(Neighbours.MID_TOP, new Location(xCoordinate,yCoordinate-1));
        neighbours.put(Neighbours.MID_BOT,new Location(xCoordinate,yCoordinate+1));
        neighbours.put(Neighbours.RIGHT_TOP,new Location(xCoordinate+1,yCoordinate-1));
        neighbours.put(Neighbours.RIGHT_MID,new Location(xCoordinate+1,yCoordinate));
        neighbours.put(Neighbours.RIGHT_BOT,new Location(xCoordinate+1,yCoordinate+1));

        ArrayList<Neighbours> remove = new ArrayList<>();
        for(Map.Entry<Neighbours, Location> entry : neighbours.entrySet()) {
            Neighbours key = entry.getKey();
            Location location = entry.getValue();

            // do what you have to do here
            // In your case, another loop.
            if (location.getXCord()>=gridLength || location.getXCord()<0){
                remove.add(key);
            }
            else if (location.getYCord()>=gridHeight || location.getYCord()<0){
                remove.add(key);
            }
        }
        for (Neighbours l:
             remove) {
            neighbours.remove(l);
        }

        //Checks for walls
        return neighbours;
    }

    public String getLocationString() {
        return "X "+xCord+" Y "+yCord;
    }
}
