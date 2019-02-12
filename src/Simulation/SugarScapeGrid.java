package Simulation;

import static Simulation.SugarScapeState.*;

public class SugarScapeGrid extends BasicGrid{
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
