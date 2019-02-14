package Simulation;

/**
 * Fire simulation grid. Grid is bounded.
 */
public class FireGrid extends BasicGrid {
    /**
     * Constructor, requires simulation specific object types
     * @param height
     * @param width
     * @param initInts
     * @param ruleSet
     * @param neighborhood
     */
    public FireGrid(int height, int width, int[][] initInts, FireRuleSet ruleSet, Neighborhood neighborhood) {
        super(height,width,neighborhood, ruleSet, GridType.BOUNDED);
        for (State t: FireState.values()) {
            cellCounts.put(t,0);
        }
        FireCell[][] initCells = new FireCell[height][width];
        for (int k = 0; k < height; k++) {
            for (int j = 0; j < width; j++) {
                switch (initInts[k][j]) {
                    case 0:
                        initCells[k][j] = new FireCell(k, j, FireState.EMPTY);
                        break;
                    case 1:
                        initCells[k][j] = new FireCell(k, j, FireState.TREE);
                        break;
                    case 2:
                        initCells[k][j] = new FireCell(k, j, FireState.BURNING);
                        break;
                }
            }
        }
        this.cells = initCells;
    }
}
