package Simulation;

public class FireCell extends Cell{
    public FireCell(int xPosition, int yPosition, FireState initState){
        location = new SquareLocation(xPosition, yPosition);
        currentState = initState;
        nextState = initState;
    }
}
