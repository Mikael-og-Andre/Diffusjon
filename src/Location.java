import java.util.ArrayList;

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
    public static ArrayList<Location> getNeighbourCordsWalled(Location loc,int gridLength, int gridHeight){
        int xCoordinate = loc.getXCord();
        int yCoordinate = loc.getYCord();

        ArrayList<Location> neighbours = new ArrayList<>();
        neighbours.add(new Location(xCoordinate-1,yCoordinate-1));
        neighbours.add(new Location(xCoordinate-1,yCoordinate));
        neighbours.add(new Location(xCoordinate-1,yCoordinate+1));
        neighbours.add(new Location(xCoordinate,yCoordinate-1));
        neighbours.add(new Location(xCoordinate,yCoordinate+1));
        neighbours.add(new Location(xCoordinate+1,yCoordinate-1));
        neighbours.add(new Location(xCoordinate+1,yCoordinate));
        neighbours.add(new Location(xCoordinate+1,yCoordinate+1));

        ArrayList<Location> remove = new ArrayList<>();
        for (Location location:
             neighbours) {
            if (location.getXCord()>=gridLength || location.getXCord()<0){
                remove.add(location);
            }
            else if (location.getYCord()>=gridHeight || location.getYCord()<0){
                remove.add(location);
            }
        }
        for (Location l:
             remove) {
            neighbours.remove(l);
        }


        //Checks for walls
        return neighbours;
    }
}
