package Simulation;

import static Simulation.PredatorPreyState.*;

public class PredatorPreyGrid extends BasicGrid {

    public PredatorPreyGrid(int height, int width, int[][] initInts, PredatorPreyRuleSet ruleSet, Neighborhood neighborhood) {
        super(height,width,neighborhood, ruleSet, GridType.TOROIDAL);
        for (State t: PredatorPreyState.values()) {
            cellCounts.put(t,0);
        }
        PredatorPreyCell[][] initCells = new PredatorPreyCell[height][width];

        for (int k = 0; k < height; k++) {
            for (int j = 0; j < width; j++) {
                PredatorPreyState newState;
                switch (initInts[k][j]) {
                    case 0:
                        newState = EMPTY;
                        break;
                    case 1:
                       newState = FISH;
                        break;
                    case 2:
                        newState = SHARK;
                        break;
                    default:
                        newState = EMPTY;
                }

                initCells[k][j] = new PredatorPreyCell(k, j, newState,ruleSet.getParameters().get("sharkEnergy"),ruleSet.getParameters().get(
                        "fishReproductionTime"),
                        ruleSet.getParameters().get("sharkReproductionTime"));
            }
        }
        this.cells = initCells;
        this.ruleSet = ruleSet;
    }
}
