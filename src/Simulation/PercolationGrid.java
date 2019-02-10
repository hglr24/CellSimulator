package Simulation;

import java.util.ArrayList;

import static Simulation.PercolationState.*;

public class PercolationGrid extends BasicGrid{
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

    public int[][] getIntArray(int height, int width){
        int[][] intArray = new int[height][width];
        for(int k = 0; k < height; k++){
            for(int j = 0; j < height; j++){
                if(this.cells[k][j].getCurrentState() == OPEN){
                    intArray[k][j] = 0;
                }
                else if(this.cells[k][j].getCurrentState() == BLOCKED){
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
