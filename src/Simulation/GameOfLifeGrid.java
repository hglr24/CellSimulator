package Simulation;

import static Simulation.GameOfLifeState.DEAD;
import static Simulation.GameOfLifeState.LIVE;

public class GameOfLifeGrid extends BasicGrid {

    public GameOfLifeGrid(int height, int width, int[][] initInts, GameOfLifeRuleSet ruleSet, Neighborhood neighborhood) {
        super(height,width,neighborhood, ruleSet, GridType.BOUNDED);
        for (State t: GameOfLifeState.values()) {
            cellCounts.put(t,0);
        }
        GameOfLifeCell[][] initCells = new GameOfLifeCell[height][width];
        for (int k = 0; k < height; k++) {
            for (int j = 0; j < width; j++) {
                switch (initInts[k][j]) {
                    case 0:
                        initCells[k][j] = new GameOfLifeCell(k, j, DEAD);
                        break;
                    case 1:
                        initCells[k][j] = new GameOfLifeCell(k, j, LIVE);
                        break;
                }
            }
        }
        this.cells = initCells;
    }
}
