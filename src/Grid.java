import java.util.HashMap;
import java.util.Map;

public class Grid {

    private Cell[][] grid;
    private int gridLength;
    private int gridHeight;
    private Randomizer randomizer;
    private Probabilities probabilities;

    public int getGridLength() {
        return gridLength;
    }

    public int getGridHeight() {
        return gridHeight;
    }

    public Grid(int gridLength, int gridHeight, Randomizer randomizer, Probabilities probabilities){
        grid = new Cell[gridLength][gridHeight];
        this.gridLength = gridLength;
        this.gridHeight = gridHeight;
        this.randomizer = randomizer;
        this.probabilities = probabilities;
        setupGrid();
    }

    //Spawns cells and makes grid array
    public void setupGrid(){
        initCellsInGrid();
        assignNeighbourCells();
    }

    public Cell getCell(int x, int y){
        return grid[x][y];
    }

    public Cell getCell(Location location){
        return grid[location.getXCord()][location.getYCord()];
    }

    //puts cells in grid, could also be used in reset
    public void initCellsInGrid(){
        for(int x = 0; x<gridLength; x++){
            for(int y = 0; y<gridHeight; y++){
                Location loc = new Location(x,y);
                Cell cell = new Cell(loc,randomizer, probabilities);
                grid[x][y] = cell;
            }
        }
    }

    //Assigns neighbours
    private void assignNeighbourCells() {
        for(int x = 0; x<gridLength; x++){
            for(int y = 0; y<gridHeight; y++){
                Cell currentCell = grid[x][y];
                HashMap<Neighbours,Location> neighbours = Location.getNeighbourCordsWalled(currentCell.getCellLocation(),gridLength,gridHeight);
                for(Map.Entry<Neighbours, Location> entry : neighbours.entrySet()) {
                    Neighbours key = entry.getKey();
                    Location location = entry.getValue();

                    currentCell.addNeighbouringCell(key,this.getCell(location));
                }
            }
        }
    }

}
