import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Cell {

    // list of all particles in that cell
    private ArrayList<Particle> particles;

    private ArrayList<Particle> removeAfter;

    //List of cells this cell can move particles to
    private HashMap<Neighbours,Cell> neighbouringCells;

    //Location of cell in grid
    private Location cellLocation;

    //Randomizer
    private Randomizer randomizer;

    private Probabilities probabilities;


    public Cell(Location loc, Randomizer randomizer, Probabilities probabilities){
        particles = new ArrayList<>();
        this.randomizer = randomizer;
        this.probabilities = probabilities;
        neighbouringCells = new HashMap<>();
        this.cellLocation = loc;
    }

    public void addParticle(Particle particle){

        particles.add(particle);
    }
    public void removeParticle(Particle particle){
        particles.remove(particle);
    }

    public void moveParticles(){

        removeAfter = new ArrayList<>();

        for (Particle particle:
             particles) {
            double p = randomizer.getRandom();
            Neighbours neighbourCell = probabilities.getCellSpot(p);
            if (neighbourCell.equals(Neighbours.MID_MID)){
                //Stay still
            }
            else if (checkNeighbour(neighbourCell)){
                Cell cell = neighbouringCells.get(neighbourCell);
                cell.addParticle(particle);
                removeAfter.add(particle);
            }
            else {
                //Cell not there
            }
        }
        for (Particle particle:
             removeAfter) {
            particles.remove(particle);
        }
    }

    public void addNeighbouringCell(Neighbours neighbour, Cell cell){
        neighbouringCells.put(neighbour,cell);
    }

    public void simulateOneStep(){
        //System.out.println("Cell at X "+cellLocation.getXCord()+" Y "+cellLocation.getYCord()+" has been simulated");
        moveParticles();
    }

    public boolean checkNeighbour(Neighbours neighbour){
        for(Map.Entry<Neighbours, Cell> entry : neighbouringCells.entrySet()) {
            Neighbours key = entry.getKey();
            Cell cellIN = entry.getValue();
            if (key.equals(neighbour)){
                return true;
            }
        }
        return false;
    }

    public ArrayList<Particle> getParticles() {
        return particles;
    }

    public HashMap<Neighbours, Cell> getNeighbouringCells() {
        return neighbouringCells;
    }

    public Location getCellLocation() {
        return cellLocation;
    }

    public int getParticleCount() {
        int particleCount = 0;
        for (Particle particle:
             particles) {
            particleCount++;
        }
        return particleCount;
    }
}
