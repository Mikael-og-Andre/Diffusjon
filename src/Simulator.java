import java.util.ArrayList;

public class Simulator {

    //Grid size
    private int gridLength;
    private int gridHeight;

    //grid
    private Grid grid;

    public Simulator(int gridLength, int gridHeight){
        grid = new Grid(gridLength,gridHeight);
    }


    public void simulateSteps(int steps) {
        for(int i = 0; i<steps; i++){
            simulateOneStep();
        }
    }

    public void simulateOneStep(){
        for(int x = 0; x<grid.getGridLength(); x++){
            for(int y = 0; y<grid.getGridHeight(); y++){
                Cell currentCell = grid.getCellByCords(x,y);
                currentCell.simulateOneStep();
            }
        }
    }
}
