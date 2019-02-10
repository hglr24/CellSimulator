package Simulation;

import java.util.ArrayList;

import static Simulation.SegregationState.*;

public class SegregationGrid extends BasicGrid {
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

    public int[][] getIntArray(int height, int width){
        int[][] intArray = new int[height][width];
        for(int k = 0; k < height; k++){
            for(int j = 0; j < height; j++){
                if(this.cells[k][j].getCurrentState() == SegregationState.EMPTY){
                    intArray[k][j] = 0;
                }
                else if(this.cells[k][j].getCurrentState() == SegregationState.A){
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
