package Simulation;

import java.util.Collection;
import java.util.Collections;

public class FireGrid extends BasicGrid {

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

    public int[][] getIntArray(int height, int width){
        int[][] intArray = new int[height][width];
        for(int k = 0; k < height; k++){
            for(int j = 0; j < height; j++){
                if(this.cells[k][j].getCurrentState() == FireState.EMPTY){
                    intArray[k][j] = 0;
                }
                else if(this.cells[k][j].getCurrentState() == FireState.TREE){
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
