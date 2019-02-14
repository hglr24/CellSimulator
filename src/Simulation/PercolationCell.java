package Simulation;

/**
 * Percolation Cell
 */
public class PercolationCell extends Cell {
    private int myYPosition;

    /**
     * Instantiate cell
     * @param xPosition
     * @param yPosition
     * @param initState
     */
    public PercolationCell(int xPosition, int yPosition, PercolationState initState){
        location = new SquareLocation(xPosition, yPosition);
        currentState = initState;
        nextState = initState;
        myYPosition = xPosition;
    }

    /**
     * Need this getter specifically to check for the simulation percolated condition
     * @return
     */
    public int getYPosition(){
        return myYPosition;
    }
}
