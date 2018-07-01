/**
 * Creates a tetris game on a grid using blocks
 * 
 * @author  Anna Wang
 * @version January 9, 2017
 */
public class Tetris implements ArrowListener
{

    private MyBoundedGrid<Block> grid;
    private BlockDisplay display;
    private Tetrad tetrad;

    /**
     * Rotates the tetrad
     */
    public void upPressed()
    {
        tetrad.rotate();
        display.showBlocks();
    }

    /**
     * Translates the tetrad one down
     */
    public void downPressed()
    {
        tetrad.translate(1,0);
        display.showBlocks();
    }

    /**
     * Translates the tetrad one left
     */
    public void leftPressed()
    {
        tetrad.translate(0,-1);
        display.showBlocks();
    }

    /**
     * Translates the tetrad one right
     */
    public void rightPressed()
    {
        tetrad.translate(0,1);
        display.showBlocks();
    }

    /**
     * Constructs a Tetris game.
     */
    public Tetris()
    {
        grid= new MyBoundedGrid<Block>(20,10);
        display= new BlockDisplay(grid);
        display.setArrowListener(this);
        display.setTitle("Tetris");
        tetrad=new Tetrad(grid);
        display.showBlocks();

    }

    /**
     * Plays the tetris game
     */
    public void play()
    {
        while(true)
        {            
            try
            {
                //Pause for 1000 milliseconds.              
                Thread.sleep(1000);
                tetrad.translate(1,0);
                display.showBlocks();
            }
            catch (InterruptedException e)
            {
                //ignore
            }
            if(!tetrad.translate(1,0))
            {
                tetrad=new Tetrad(grid);
                for (int i=0; i<grid.getNumRows(); i++)
                {
                    clearRow(i);
                }
            }
        }
    }
    /**
     * Checks if it's a completed row
     * @param row the row it is checking
     * @return  true if it can be completed; otherwise
     *          false
     */
    private boolean isCompletedRow(int row)
    {
        for (int i=0; i<grid.getNumCols(); i++)
        {
            Location temp= new Location(row, i);
            if (grid.get(temp)==null)
            {
                return false;
            }
        }
        return true;
    }
    /**
     * Clears the row
     * @param   row the row it is clearing
     */
    private void clearRow(int row)
    {
        if (isCompletedRow(row))
        {
            for (int i=0; i<grid.getNumCols(); i++)
            {
                Location temp= new Location(row, i);
                Block removeThis= grid.get(temp);
                removeThis.removeSelfFromGrid();
            }

            for (int r=grid.getNumRows()-1; r>=0; r--)
            {
                for (int c=0; c<grid.getNumCols(); c++)
                {
                    Location loc=new Location(r,c);
                    if (grid.get(loc)!=null)
                    {
                        Block temporary=grid.get(loc);
                        Location newLoc= new Location(r+1,c);
                        if (grid.isValid(newLoc))
                        {
                            temporary.moveTo(newLoc);
                        }
                    }
                }
            }
        }
    }

    /**
     * Plays the game
     *
     * @param args arguments from the command line
     */
    public static void main(String [ ] args)
    {
        Tetris game=new Tetris();
        game.play();
    }

}
