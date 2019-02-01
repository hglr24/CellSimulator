package Simulation;


public class PredatorPreyCell extends Cell {

    public PredatorPreyCell(int xPosition, int yPosition,  initState){
        location = new SquareLocation(xPosition, yPosition);
        currentState = initState;
        nextState = initState;
    }

    @Override
    void findNeighbors() {


    }

}
