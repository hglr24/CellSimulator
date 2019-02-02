package Simulation;


public class PredatorPreyCell extends Cell {

    public PredatorPreyCell(int xPosition, int yPosition, PredatorPreyState initState){
        location = new SquareLocation(xPosition, yPosition);
        currentState = initState;
        nextState = initState;
    }


}
