import java.util.ArrayList;

public class Simulator {

    //Grid size
    private int gridLength;
    private int gridHeight;

    //grid
    private Grid grid;

    public Simulator(int gridLength, int gridHeight){
        grid = new Grid(gridLength,gridHeight);
        populateCell((gridLength/2),gridHeight/2,10);
    }


    public void simulateSteps(int steps) {
        for(int i = 0; i<steps; i++){
            simulateOneStep();
        }
    }

    public void simulateOneStep(){
        for(int x = 0; x<grid.getGridLength(); x++){
            for(int y = 0; y<grid.getGridHeight(); y++){
                Cell currentCell = grid.getCell(x,y);
                currentCell.simulateOneStep();
            }
        }
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
