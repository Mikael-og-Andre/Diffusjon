import java.util.ArrayList;

public class Cell {

    // list of all particles in that cell
    private ArrayList<Particle> particles;

    //List of cells this cell can move particles to
    private ArrayList<Cell> neighbouringCells;

    //Location of cell in grid
    private Location cellLocation;

    public Cell(Location loc){
        particles = new ArrayList<>();
        neighbouringCells = new ArrayList<>();
        this.cellLocation = loc;
    }

    public void addParticle(Particle particle){
        particles.add(particle);
    }
    public void removeParticle(Particle particle){
        particles.remove(particle);
    }

    public void moveParticle(Cell cell, Particle particle){
        if(checkIfCellInNeighbours(cell)){
            this.addParticle(particle);
            cell.removeParticle(particle);
        }
    }

    public void addNeighbouringCell(Cell cell){
        //System.out.println(" cell "+cellLocation.getXCord()+" "+cellLocation.getYCord()+"added cell at "+cell.cellLocation.getXCord()+" "+cell.cellLocation.getYCord());
        neighbouringCells.add(cell);
    }

    public void simulateOneStep(){
        //System.out.println("Cell at X "+cellLocation.getXCord()+" Y "+cellLocation.getYCord()+" has been simulated");

    }

    public boolean checkIfCellInNeighbours(Cell cell){
        for (Cell c:
             neighbouringCells) {
            if (cell.equals(c)){
                return true;
            }
        }
        return false;
    }

    public ArrayList<Particle> getParticles() {
        return particles;
    }

    public ArrayList<Cell> getNeighbouringCells() {
        return neighbouringCells;
    }

    public Location getCellLocation() {
        return cellLocation;
    }
}
