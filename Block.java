import java.awt.Color;
/**
 * Block class maintains information about a block.
 * This class is extremely flexible to multiple games;
 * such as Tile Game, Tetris, and Tic-Tac-Toe.
 * 
 * @author  Dave Feinberg
 * @author  Richard Page
 * @author  Susan King     Added documentation
 * @author  Anna Wang
 * @version January 4, 2017
 */
public class Block
{
    private MyBoundedGrid<Block> grid;
    private Location location;
    private Color color;
    private String text;

    /**
     * Constructs a blue block.
     */
    public Block()
    {
        color = Color.BLUE;
        grid = null;
        location = null;
    }

    /**
     * Gets the color of this block.
     * 
     * @return the color of this block
     */
    public Color getColor()
    {
        return this.color;
    }

    /**
     * Sets the color of this block to newColor.
     * 
     * @param newColor  the new color of this block
     */
    public void setColor(Color newColor)
    {
        this.color=newColor;
    }

    /**
     * Gets the grid of this block, or null if this block is not contained
     * in a grid.
     * 
     * @return the grid
     */
    public MyBoundedGrid<Block> getGrid()
    {
        return this.grid;

    }

    /**
     * Gets the location of this block, or null if this block is not contained
     * in a grid.
     * 
     * @return this block's location, or null if this block is not in the grid
     */
    public Location getLocation()
    {
        return this.location;
    }

    /**
     * Removes this block from its grid.
     *
     * @precondition  this block is contained in a grid
     */
    public void removeSelfFromGrid()
    {
        this.grid.remove(this.getLocation());
        this.grid=null;
        this.location=null;
    }

    /**
     * Puts this block into location loc of grid gr.
     * If there is another block at loc, it is removed.
     * 
     * @precondition  (1) this block is not contained in a grid
     *                (2) loc is valid in gr
     *               
     * @param gr  the grid to place this block
     * @param loc the location to place this block
     */
    public void putSelfInGrid(MyBoundedGrid<Block> gr, Location loc)
    {
        Block old= gr.put(loc,this);
        grid=gr;
        location=loc;
        if(old!=null)
        {
            old.grid=null;
            old.location=null;
        }
    }

    /**
     * Moves this block to newLocation.
     * If there is another block at newLocation, it is removed.
     *
     * @precondition  (1) this block is contained in a grid
     *                (2) newLocation is valid in the grid of this block
     *                
     * @param newLocation  the location that the block is to be moved
     */
    public void moveTo(Location newLocation)
    {
        Location oldLocation=this.getLocation();
        grid.remove(oldLocation);
        putSelfInGrid(grid, newLocation);
    }

    /**
     * Returns a string with the location and color of this block.
     * 
     * @return location and color information about the block
     */
    public String toString()
    {
        return "Block[location=" + location + ",color=" + color + "]";
    }
}