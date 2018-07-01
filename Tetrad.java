import java.awt.Color;
/**
 * Creates tetrads using blocks 
 * @author  Anna Wang
 * @version January 9, 2017
 */
public class Tetrad 
{
    private Block[] tetrad;
    private MyBoundedGrid<Block> grid;
    private int shape;
    private final static Color[] BLOCK_COLORS={Color.RED, Color.GRAY,
                                               Color.CYAN, Color.YELLOW,
                                               Color.MAGENTA, Color.BLUE,
                                               Color.GREEN};
    /**
     * Constructor for objects of class Tetrad
     * @param aGrid the grid for the tetrad
     */
    public Tetrad(MyBoundedGrid<Block> aGrid)
    {
        grid=aGrid;
        int col=grid.getNumCols();
        shape=(int)(Math.random()*BLOCK_COLORS.length);
        //shape=0;
        Location[]locs = new Location[4];
        if (shape==0)
        {
            locs[0]= new Location(0,4);
            locs[1]= new Location(1,4);
            locs[2]= new Location(2,4);
            locs[3]= new Location(3,4);
        }
        else if (shape==1)
        {
            locs[0]= new Location(0,3);
            locs[1]= new Location(0,4);
            locs[2]= new Location(1,4);
            locs[3]= new Location(0,5);
        }
        else if (shape==2)
        {
            locs[0]= new Location(0,4);
            locs[1]= new Location(1,4);
            locs[2]= new Location(0,5);
            locs[3]= new Location(1,5);
        }
        else if (shape==3)
        {
            locs[0]= new Location(2,5);
            locs[1]= new Location(0,4);
            locs[2]= new Location(1,4);
            locs[3]= new Location(2,4);
        }
        else if (shape==4)
        {
            locs[0]= new Location(2,4);
            locs[1]= new Location(0,5);
            locs[2]= new Location(1,5);
            locs[3]= new Location(2,5);
        }
        else if (shape==5)
        {
            locs[0]= new Location(1,3);
            locs[1]= new Location(1,4);
            locs[2]= new Location(0,4);
            locs[3]= new Location(0,5);
        }
        else if(shape==6)
        {
            locs[0]= new Location(1,4);
            locs[1]= new Location(1,5);
            locs[2]= new Location(0,3);
            locs[3]= new Location(0,4);
        }
        addToLocations(grid,locs);
    }
    
    /**
     * Removes blocks and returns an array of locations it removed
     * @return array of locations it removed
     */
    private Location[] removeBlocks()
    {
        Location[] myLocs= new Location[4];
        for(int i=0; i<4;i++)
        {
            myLocs[i]=tetrad[i].getLocation();
            tetrad[i].removeSelfFromGrid();
        }
        return myLocs;
    }
    /**
     * Checks if locs is empty
     * @param gridd  the grid to check
     * @param locs  The array of locations to check
     */
    private boolean areEmpty (MyBoundedGrid<Block> gridd, Location[] locs)
    {
        for (int i=0; i<locs.length;i++)
        {
            if(!gridd.isValid(locs[i])|| gridd.get(locs[i])!= null)
            {
                return false;
            }
        }
        return true;
    }

    /**
     * Adds the tetrad to a certain location
     * @param gr the grid to put the location in
     * @param locs  the location to put the tetrad in
     */
    private void addToLocations(MyBoundedGrid<Block>gr,Location[]locs)
    {
        tetrad= new Block[locs.length];
        for(int i=0; i<locs.length;i++)
        {
            tetrad[i]=new Block();
            tetrad[i].putSelfInGrid(gr,locs[i]);
            tetrad[i].setColor(BLOCK_COLORS[shape]);
        }
    }
    /**
     * Translate the tetrad to a position
     * @param deltaRow  the amount to translate the row down
     * @param deltaCol  the amount to translate the col right
     * @return true if it can translate to the new position; otherwise
     *         false
     */
    public boolean translate (int deltaRow, int deltaCol)
    {
        Location[] oldLocs= removeBlocks();
        Location[] newLocs= new Location[4];
        for (int i=0; i<oldLocs.length; i++)
        {
            newLocs[i]=new Location(oldLocs[i].getRow()+deltaRow, 
                oldLocs[i].getCol()+deltaCol);
        }
        if (areEmpty(grid, newLocs))
        {
            addToLocations(grid, newLocs);
            return true;
        }
        addToLocations(grid, oldLocs);
        return false;
    }
    /**
     * Rotates around a pivot point. Pivot point is block 0 on the tetrad.
     * @return true if it can rotate to the new position else false
     */
    public boolean rotate()
    {
        if(shape==2)
        {
            return true;
        }
        Location pivot= tetrad[0].getLocation();
        int rowPivot=pivot.getRow();
        int colPivot=pivot.getCol();
        Location[] oldLocs= removeBlocks();
        Location[] newLocs= new Location[4];
        newLocs[0]=pivot;
        for (int i=1; i<oldLocs.length; i++)
        {
            newLocs[i]=new Location(rowPivot-colPivot+oldLocs[i].getCol(),
                rowPivot+colPivot-oldLocs[i].getRow());
        }
        if (areEmpty(grid, newLocs))
        {
            addToLocations(grid, newLocs);
            return true;
        }
        else
        {
            addToLocations(grid, oldLocs);
            return false;
        }
    }

}
