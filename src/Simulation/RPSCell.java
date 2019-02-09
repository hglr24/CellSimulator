package Simulation;

public class RPSCell extends Cell {

    public RPSCell(int xPosition, int yPosition, RPSState initState){
        location = new SquareLocation(xPosition, yPosition);
        currentState = initState;
        nextState = initState;
    }

}
