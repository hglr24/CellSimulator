package Simulation;


public class GameOfLifeCell extends Cell {

    public GameOfLifeCell(int xPosition, int yPosition, GameOfLifeState initState){
        location = new SquareLocation(xPosition, yPosition);
        currentState = initState;
        nextState = initState;
    }

    @Override
    void findNeighbors() {


    }

}
