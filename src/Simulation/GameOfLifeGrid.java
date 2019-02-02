package Simulation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GameOfLifeGrid extends BasicGrid {
    private int height;
    private int width;
    private GameOfLifeCell [][] cells;

    GameOfLifeGrid(int height, int width, GameOfLifeCell[][] initCells){
        this.height = height;
        this.width = width;
        this.cells = initCells;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public ArrayList<GameOfLifeCell> getNeighbors(Location location) {
        ArrayList<GameOfLifeCell> neighbors = new ArrayList<>();

        return neighbors;
    }

    @Override
    public GameOfLifeCell[][] getCells() {
        return cells;
    }

    @Override
    public Cell getCell(Location location) {
        if(!validLocation(location) || !(location instanceof  SquareLocation))
            throw new IllegalArgumentException("Invalid Location");
        SquareLocation sl = (SquareLocation) location;
        return cells[sl.getX()][sl.getY()];
    }
}
