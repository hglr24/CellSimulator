package Simulation;

import java.util.ArrayList;

import static Simulation.SegregationState.*;

public class SegregationGrid extends BasicGrid{
     public SegregationGrid(int height, int width, int[][] initInts, SegregationRuleSet ruleSet) {
        this.height = height;
        this.width = width;
        SegregationCell[][] initCells = new SegregationCell[height][width];
        for(int k = 0; k < height; k++){
            for(int j = 0; j < width; j++){
                switch(initInts[k][j]){
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
        this.ruleSet = ruleSet;
    }


    @Override
    public ArrayList<SegregationCell> findNeighbors(Location location) {
         return getAdjacentNeighbors(location);
    }

}
