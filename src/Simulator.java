import java.util.ArrayList;

public class Simulator {

    //Grid size
    private int gridLength;
    private int gridHeight;

    //grid
    private Grid grid;

    //Time
    private int time;

    //Visuals
    private SimulatorView simView;

    //Probability distro
    private Probabilities probabilities;

    public Simulator(int gridLength, int gridHeight,long seed, Probabilities probabilities, int initalPop){
        this.probabilities = probabilities;
        grid = new Grid(gridLength,gridHeight,new Randomizer(seed),probabilities);
        simView = new SimulatorView(grid, initalPop);
        populateCell((gridLength/2),gridHeight/2,initalPop);
        time = 0;
    }


    public void simulateSteps(int steps) {
        for(int i = 0; i<steps; i++){
            simulateOneStep();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void simulateOneStep(){
        for(int x = 0; x<grid.getGridLength(); x++){
            for(int y = 0; y<grid.getGridHeight(); y++){
                Cell currentCell = grid.getCell(x,y);
                currentCell.simulateOneStep();
            }
        }
        simView.showStatus(time,grid);
        time++;
    }

    public void populateCell(int x,int y,int amount){
        Cell cell = grid.getCell(x,y);
        for(int i = 0; i<amount; i++){
            cell.addParticle(new Particle(cell.getCellLocation()));
        }
    }

    public void populateCell(Location location,int amount){
        Cell cell = grid.getCell(location);
        for(int i = 0; i<amount; i++){
            cell.addParticle(new Particle(cell.getCellLocation()));
        }
    }
}
