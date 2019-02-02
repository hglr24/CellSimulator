package Simulation;

import java.util.ArrayList;


public class GameOfLifeGrid extends BasicGrid {
    private int height;
    private int width;
    private GameOfLifeCell[][] cells;
    private GameOfLifeRuleSet ruleSet;

    public GameOfLifeGrid(int height, int width, GameOfLifeCell[][] initCells, GameOfLifeRuleSet ruleSet) {
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
        //all 8 neighbors

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
