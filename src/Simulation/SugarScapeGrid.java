package Simulation;

import static Simulation.SugarScapeState.*;

/**
 * Takes in the integer array and creates an array of cells with states mapped to  the integers
 */
public class SugarScapeGrid extends BasicGrid{
    /**
     * Grid object that is passed to the ruleset to be updated and represent the state of the simulation
     * @param height is the number of rows in the simulation
     * @param width is the number of columns in the simulation
     * @param initInts is the 2d integer array representing the starting states of the simulation
     * @param neighborhood is the Neighborhood enumeration that should be considered when applying rules
     * @param ruleSet is the set of rules that governs the updating of the cells
     */
    public SugarScapeGrid(int height, int width, int[][] initInts, Neighborhood neighborhood, SegregationRuleSet ruleSet){
        super(height, width, neighborhood, ruleSet, GridType.BOUNDED);
        SugarScapeCell[][] initCells = new SugarScapeCell[height][width];
        for (int k = 0; k < height; k++) {
            for (int j = 0; j < width; j++) {
                switch (initInts[k][j]) {
                    case 0:
                        initCells[k][j] = new SugarScapeCell(k, j, SUGAR0, false, 3);
                        break;
                    case 1:
                        initCells[k][j] = new SugarScapeCell(k, j, SUGAR1, false, 3);
                        break;
                    case 2:
                        initCells[k][j] = new SugarScapeCell(k, j, SUGAR2, false, 3);
                        break;
                }
            }
        }
    }
}
