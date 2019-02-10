package Simulation;

import static Simulation.RPSState.*;

public class RPSGrid extends BasicGrid {

    public RPSGrid(int height, int width, int[][] initInts, RPSRuleSet ruleSet, Neighborhood neighborhood) {
        super(height,width,neighborhood, ruleSet, GridType.BOUNDED);
        for (State t: RPSState.values()) {
            cellCounts.put(t,0);
        }
        RPSCell[][] initCells = new RPSCell[height][width];
        for (int k = 0; k < height; k++) {
            for (int j = 0; j < width; j++) {
                switch (initInts[k][j]) {
                    case 0:
                        initCells[k][j] = new RPSCell(k, j, ROCK);
                        break;
                    case 1:
                        initCells[k][j] = new RPSCell(k, j,PAPER);
                        break;
                    case 2:
                        initCells[k][j] = new RPSCell(k, j, SCISSORS);
                        break;
                }
            }
        }
        this.cells = initCells;
        this.ruleSet = ruleSet;
    }
}
