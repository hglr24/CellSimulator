package Simulation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static Simulation.GameOfLifeState.DEAD;
import static Simulation.GameOfLifeState.LIVE;

public class GameOfLifeGrid extends BasicGrid {
    private int height;
    private int width;
    private GameOfLifeCell [][] cells;

    GameOfLifeGrid(int height, int width, int[][] initInts){
        this.height = height;
        this.width = width;
        GameOfLifeCell[][] initCells = new GameOfLifeCell[height][width];
        for(int k = 0; k < height; k++){
            for(int j = 0; j < width; j++){
                switch(initInts[k][j]){
                    case 0:
                        initCells[k][j] = new GameOfLifeCell(k, j, DEAD);
                    case 1:
                        initCells[k][j] = new GameOfLifeCell(k, j, LIVE);
                }
            }
        }
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
