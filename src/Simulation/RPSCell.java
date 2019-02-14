package Simulation;

/**
 * Cell for RPS
 */
public class RPSCell extends Cell {
    /**
     * Instantiate
     * @param xPosition
     * @param yPosition
     * @param initState
     */
    public RPSCell(int xPosition, int yPosition, RPSState initState){
        location = new SquareLocation(xPosition, yPosition);
        currentState = initState;
        nextState = initState;
    }

}
