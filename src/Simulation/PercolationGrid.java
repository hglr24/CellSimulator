package Simulation;

import static Simulation.PercolationState.*;

/**
 * Grid for Percolation
 */
public class PercolationGrid extends BasicGrid{
    /**
     * Instantiation. Uses bounded grid.
     * @param height
     * @param width
     * @param initInts
     * @param ruleSet
     * @param neighborhood
     */
    public PercolationGrid(int height, int width, int[][] initInts, PercolationRuleSet ruleSet, Neighborhood neighborhood) {
        super(height,width,neighborhood, ruleSet, GridType.BOUNDED);
        for (State t: PercolationState.values()) {
            cellCounts.put(t,0);
        }
        PercolationCell[][] initCells = new PercolationCell[height][width];
        for(int k = 0; k < height; k++){
            for(int j = 0; j < width; j++){
                switch(initInts[k][j]){
                    case 0:
                        initCells[k][j] = new PercolationCell(k, j, OPEN);
                        break;
                    case 2:
                        initCells[k][j] = new PercolationCell(k, j, PERCOLATED);
                        break;
                    case 1:
                        initCells[k][j] = new PercolationCell(k, j, BLOCKED);
                        break;
                }
            }
        }
        this.cells = initCells;
    }
}
