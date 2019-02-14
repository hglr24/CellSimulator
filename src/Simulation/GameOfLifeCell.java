package Simulation;

/**
 * Game of Life cell simulation. Option to use a different location type
 */
public class GameOfLifeCell extends Cell {

    public GameOfLifeCell(int xPosition, int yPosition, GameOfLifeState initState){
        location = new SquareLocation(xPosition, yPosition);
        currentState = initState;
        nextState = initState;
    }

}
