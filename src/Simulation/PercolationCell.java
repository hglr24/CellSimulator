package Simulation;

public class PercolationCell extends Cell {
    public PercolationCell(int xPosition, int yPosition, PercolationState initState){
        location = new SquareLocation(xPosition, yPosition);
        currentState = initState;
        nextState = initState;
    }
}
