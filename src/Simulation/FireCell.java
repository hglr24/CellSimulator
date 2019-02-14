package Simulation;

/**
 * Specific cell for fire simulation
 */
public class FireCell extends Cell{
    /**
     * Instantiate. Requires a FireState
     * @param xPosition
     * @param yPosition
     * @param initState
     */
    public FireCell(int xPosition, int yPosition, FireState initState){
        location = new SquareLocation(xPosition, yPosition);
        currentState = initState;
        nextState = initState;
    }
}
