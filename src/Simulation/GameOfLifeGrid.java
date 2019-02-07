package Simulation;

import java.util.ArrayList;


import static Simulation.GameOfLifeState.DEAD;
import static Simulation.GameOfLifeState.LIVE;

public class GameOfLifeGrid extends BasicGrid {

    public GameOfLifeGrid(int height, int width, int[][] initInts, GameOfLifeRuleSet ruleSet) {
        this.height = height;
        this.width = width;
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
        this.ruleSet = ruleSet;
    }


    @Override
    public ArrayList<GameOfLifeCell> findNeighbors(Location location) {
        return getAdjacentNeighbors(location);
    }


}
