import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Cell {

    // list of all particles in that cell
    private ArrayList<Particle> particles;


    //List of cells this cell can move particles to
    private HashMap<Neighbours,Cell> neighbouringCells;

    //Location of cell in grid
    private Location cellLocation;

    //Randomizer
    private Randomizer randomizer;


    public Cell(Location loc, Randomizer randomizer){
        particles = new ArrayList<>();
        this.randomizer = randomizer;
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

        ArrayList<Particle> removeAfter = new ArrayList<>();
        for (Particle particle:
             particles) {
            double p = randomizer.getRandom();
            if (p<=0.5){
                //Dont move
            }
            else if (p>0.5){
                moveParticleToRandomNeighbour(particle);
                removeAfter.add(particle);
            }
            else{

            }
        }
        for (Particle particle:
             removeAfter) {
            particles.remove(particle);
        }
    }

    private void moveParticleToRandomNeighbour(Particle particle) {
        int neighbourCount = 8;
        double chance = neighbourCount*randomizer.getRandom();
        long selected = Math.round(chance);
    }


    public void addNeighbouringCell(Neighbours neighbour, Cell cell){
        neighbouringCells.put(neighbour,cell);
    }

    public void simulateOneStep(){
        //System.out.println("Cell at X "+cellLocation.getXCord()+" Y "+cellLocation.getYCord()+" has been simulated");
        moveParticles();
    }

    public boolean checkIfCellInNeighbours(Cell cell){
        for(Map.Entry<Neighbours, Cell> entry : neighbouringCells.entrySet()) {
            Neighbours key = entry.getKey();
            Cell cellIN = entry.getValue();
            if (cell.equals(cellIN)){
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
