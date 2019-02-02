package Simulation;

import java.util.ArrayList;


public class GameOfLifeGrid extends BasicGrid {
    private int height;
    private int width;
    private GameOfLifeCell[][] cells;
    private GameOfLifeRuleSet ruleSet;

    GameOfLifeGrid(int height, int width, GameOfLifeCell[][] initCells, GameOfLifeRuleSet ruleSet) {
        this.height = height;
        this.width = width;
        this.cells = initCells;
        this.ruleSet = ruleSet;
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
    public ArrayList<GameOfLifeCell> findNeighbors(Location location) {
        ArrayList<GameOfLifeCell> neighbors = new ArrayList<>();

        SquareLocation sl = (SquareLocation) location;
        //all 8 neighbors
        int[] x = {-1, 0, 1};
        for (int i : x) {
            for (int j : x) {
                if (i == 0 && j == 0)
                    continue;
                SquareLocation temp = new SquareLocation(sl.getX() + i, sl.getY() + j);
                if (validLocation(temp))
                    neighbors.add((GameOfLifeCell) getCell(temp));
            }
        }

        return neighbors;
    }

    @Override
    public GameOfLifeCell[][] getCells() {
        return cells;
    }

    @Override
    public Cell getCell(Location location) {
        if (!validLocation(location) || !(location instanceof SquareLocation))
            throw new IllegalArgumentException("Invalid Location");
        SquareLocation sl = (SquareLocation) location;
        return cells[sl.getX()][sl.getY()];
    }

    @Override
    public RuleSet getRuleSet() {
        return ruleSet;
    }
}
