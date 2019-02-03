package Simulation;

import java.util.ArrayList;

import static Simulation.PercolationState.*;

public class PercolationGrid extends BasicGrid{
    public PercolationGrid(int height, int width, int[][] initInts, PercolationRuleSet ruleSet) {
        this.height = height;
        this.width = width;
        PercolationCell[][] initCells = new PercolationCell[height][width];
        for(int k = 0; k < height; k++){
            for(int j = 0; j < width; j++){
                switch(initInts[k][j]){
                    case 0:
                        initCells[k][j] = new PercolationCell(k, j, OPEN);
                        break;
                    case 1:
                        initCells[k][j] = new PercolationCell(k, j, PERCOLATED);
                        break;
                    case 2:
                        initCells[k][j] = new PercolationCell(k, j, BLOCKED);
                        break;
                }
            }
        }
        for(int j = 0; j < width; j++){
            if(initCells[0][j].currentState==OPEN)
                initCells[0][j] = new PercolationCell(0, j, PERCOLATED);
        }
        this.cells = initCells;
        this.ruleSet = ruleSet;
    }

    @Override
    public ArrayList<PercolationCell> findNeighbors(Location location) {
        return this.getAdjacentCells(location);
    }
}
