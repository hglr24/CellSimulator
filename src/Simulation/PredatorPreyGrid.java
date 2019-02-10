package Simulation;

import java.util.ArrayList;

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
                switch (initInts[k][j]) {
                    case 0:
                        initCells[k][j] = new PredatorPreyCell(k, j, EMPTY,ruleSet.getSharkHealth(),ruleSet.getFishReproduce(),ruleSet.getSharkReproduce());
                        break;
                    case 1:
                        initCells[k][j] = new PredatorPreyCell(k, j, FISH,ruleSet.getSharkHealth(),ruleSet.getFishReproduce(),ruleSet.getSharkReproduce());
                        break;
                    case 2:
                        initCells[k][j] = new PredatorPreyCell(k, j, SHARK,ruleSet.getSharkHealth(),ruleSet.getFishReproduce(),ruleSet.getSharkReproduce());
                        break;
                }
            }
        }
        this.cells = initCells;
        this.ruleSet = ruleSet;
    }

    public int[][] getIntArray(int height, int width){
        int[][] intArray = new int[height][width];
        for(int k = 0; k < height; k++){
            for(int j = 0; j < height; j++){
                if(this.cells[k][j].getCurrentState() == PredatorPreyState.EMPTY){
                    intArray[k][j] = 0;
                }
                else if(this.cells[k][j].getCurrentState() == PredatorPreyState.FISH){
                    intArray[k][j] = 1;
                }
                else{
                    intArray[k][j] = 2;
                }
            }
        }
        return intArray;
    }

}
