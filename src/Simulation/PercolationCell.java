package Simulation;

public class PercolationCell extends Cell {
    private int myYPosition;
    public PercolationCell(int xPosition, int yPosition, PercolationState initState){
        location = new SquareLocation(xPosition, yPosition);
        currentState = initState;
        nextState = initState;
        myYPosition = xPosition;
    }

    public int getYPosition(){
        return myYPosition;
    }
}
