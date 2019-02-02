package Simulation;

import java.util.ArrayList;

import static Simulation.SegregationState.*;

public class SegregationGrid extends BasicGrid{
     SegregationGrid(int height, int width, int[][] initInts, SegregationRuleSet ruleSet) {
        this.height = height;
        this.width = width;
        SegregationCell[][] initCells = new SegregationCell[height][width];
        for(int k = 0; k < height; k++){
            for(int j = 0; j < width; j++){
                switch(initInts[k][j]){
                    case 0:
                        initCells[k][j] = new SegregationCell(k, j, EMPTY);
                    case 1:
                        initCells[k][j] = new SegregationCell(k, j, A);
                    case 2:
                        initCells[k][j] = new SegregationCell(k, j, B);
                }
            }
        }
        this.cells = initCells;
        this.ruleSet = ruleSet;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public ArrayList<SegregationCell> findNeighbors(Location location) {
        ArrayList<SegregationCell> neighbors = new ArrayList<>();
        SquareLocation sl = (SquareLocation) location;
        //all 8 neighbors
        int[] x = {-1, 0, 1};
        for (int i : x) {
            for (int j : x) {
                if (i == 0 && j == 0)
                    continue;
                SquareLocation temp = new SquareLocation(sl.getX() + i, sl.getY() + j);
                if (validLocation(temp))
                    neighbors.add((SegregationCell) getCell(temp));
            }
        }

        return neighbors;
    }

    @Override
    public RuleSet getRuleSet() {
        return ruleSet;
    }
}
