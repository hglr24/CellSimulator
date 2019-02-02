package Simulation;

public class SegregationCell extends Cell{
    public SegregationCell(int xPosition, int yPosition, SegregationState initState){
        location = new SquareLocation(xPosition, yPosition);
        currentState = initState;
        nextState = initState;
    }

    public void setNextState(SegregationState changeNextState){
        this.nextState = changeNextState;
    }

}
