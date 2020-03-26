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

    public Simulator(int gridLength, int gridHeight,long seed){
        grid = new Grid(gridLength,gridHeight,new Randomizer(seed));
        simView = new SimulatorView(grid);
        populateCell((gridLength/2),gridHeight/2,1);
        time = 0;
    }


    public void simulateSteps(int steps) {
        for(int i = 0; i<steps; i++){
            simulateOneStep();
            try {
                Thread.sleep(10);
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
