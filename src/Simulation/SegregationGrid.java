package Simulation;

import static Simulation.SegregationState.*;

/**
 * Segregation Grid
 */
public class SegregationGrid extends BasicGrid {
    /**
     * Instantiate specific simulation
     * @param height
     * @param width
     * @param initInts
     * @param ruleSet
     * @param neighborhood
     */
    public SegregationGrid(int height, int width, int[][] initInts, SegregationRuleSet ruleSet, Neighborhood neighborhood) {
        super(height, width, neighborhood, ruleSet, GridType.BOUNDED);
        for (State t: SegregationState.values()) {
            cellCounts.put(t,0);
        }
        SegregationCell[][] initCells = new SegregationCell[height][width];
        for (int k = 0; k < height; k++) {
            for (int j = 0; j < width; j++) {
                switch (initInts[k][j]) {
                    case 0:
                        initCells[k][j] = new SegregationCell(k, j, EMPTY);
                        break;
                    case 1:
                        initCells[k][j] = new SegregationCell(k, j, A);
                        break;
                    case 2:
                        initCells[k][j] = new SegregationCell(k, j, B);
                        break;
                }
            }
        }
        this.cells = initCells;
    }
}
