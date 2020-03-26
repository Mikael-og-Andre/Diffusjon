import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * A graphical view of the simulation grid.
 * The view displays a colored rectangle for each location 
 * representing its contents. It uses a default background color.
 * Colors for each type of species can be defined using the
 * setColor method.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2016.02.29
 */
public class SimulatorView extends JFrame
{
    // Colors used for empty locations.
    private static final Color EMPTY_COLOR = Color.white;



    private final String TIME_PREFIX = "Time: ";
    private JLabel timeLabel;
    private FieldView fieldView;

    private Grid grid;

    /**
     * Grid
     */
    public SimulatorView(Grid grid)
    {
        this.grid = grid;

        setTitle("Fox and Rabbit Simulation");
        timeLabel = new JLabel(TIME_PREFIX, JLabel.CENTER);
        
        setLocation(100, 50);
        
        fieldView = new FieldView(grid.getGridLength(), grid.getGridHeight());

        Container contents = getContentPane();
        contents.add(timeLabel, BorderLayout.NORTH);
        contents.add(fieldView, BorderLayout.CENTER);
        pack();
        setVisible(true);
    }

    /**
     * @return The color to be used for a given class of animal.
     */
    private Color getColor(Cell cell)
    {
        int particlesInCell = cell.getParticleCount();
        if (particlesInCell>255) particlesInCell = 255;
        if (particlesInCell<1) return Color.white;
        return new Color(particlesInCell,0,0);
    }

    /**
     * Show the current status of the field.
     * @param time Which iteration step it is.
     * @param grid The field whose status is to be displayed.
     */
    public void showStatus(int time, Grid grid)
    {
        if(!isVisible()) {
            setVisible(true);
        }
            
        timeLabel.setText(TIME_PREFIX + time);
        
        fieldView.preparePaint();

        for(int x = 0; x < grid.getGridLength(); x++) {
            for(int y = 0; y < grid.getGridHeight(); y++) {
                Cell cell = grid.getCell(x,y);
                if(cell != null) {
                    fieldView.drawMark(x, y, getColor(cell));
                }
                else {
                    fieldView.drawMark(x, y, EMPTY_COLOR);
                }
            }
        }

        fieldView.repaint();
    }



    
    /**
     * Provide a graphical view of a rectangular field. This is 
     * a nested class (a class defined inside a class) which
     * defines a custom component for the user interface. This
     * component displays the field.
     * This is rather advanced GUI stuff - you can ignore this 
     * for your project if you like.
     */
    private class FieldView extends JPanel
    {
        private final int GRID_VIEW_SCALING_FACTOR = 6;

        private int gridWidth, gridHeight;
        private int xScale, yScale;
        Dimension size;
        private Graphics g;
        private Image fieldImage;

        /**
         * Create a new FieldView component.
         */
        public FieldView(int height, int width)
        {
            gridHeight = height;
            gridWidth = width;
            size = new Dimension(0, 0);
        }

        /**
         * Tell the GUI manager how big we would like to be.
         */
        public Dimension getPreferredSize()
        {
            return new Dimension(gridWidth * GRID_VIEW_SCALING_FACTOR,
                                 gridHeight * GRID_VIEW_SCALING_FACTOR);
        }

        /**
         * Prepare for a new round of painting. Since the component
         * may be resized, compute the scaling factor again.
         */
        public void preparePaint()
        {
            if(! size.equals(getSize())) {  // if the size has changed...
                size = getSize();
                fieldImage = fieldView.createImage(size.width, size.height);
                g = fieldImage.getGraphics();

                xScale = size.width / gridWidth;
                if(xScale < 1) {
                    xScale = GRID_VIEW_SCALING_FACTOR;
                }
                yScale = size.height / gridHeight;
                if(yScale < 1) {
                    yScale = GRID_VIEW_SCALING_FACTOR;
                }
            }
        }
        
        /**
         * Paint on grid location on this field in a given color.
         */
        public void drawMark(int x, int y, Color color)
        {
            g.setColor(color);
            g.fillRect(x * xScale, y * yScale, xScale-1, yScale-1);
        }

        /**
         * The field view component needs to be redisplayed. Copy the
         * internal image to screen.
         */
        public void paintComponent(Graphics g)
        {
            if(fieldImage != null) {
                Dimension currentSize = getSize();
                if(size.equals(currentSize)) {
                    g.drawImage(fieldImage, 0, 0, null);
                }
                else {
                    // Rescale the previous image.
                    g.drawImage(fieldImage, 0, 0, currentSize.width, currentSize.height, null);
                }
            }
        }
    }
}
