package Simulation;

import java.util.ArrayList;

import static Simulation.PredatorPreyState.*;

public class PredatorPreyGrid extends BasicGrid {

    public PredatorPreyGrid(int height, int width, int[][] initInts, PredatorPreyRuleSet ruleSet) {
        this.height = height;
        this.width = width;
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

    @Override
    public ArrayList findNeighbors(Location location) {
        ArrayList<Cell> neighbors = new ArrayList<>();

        SquareLocation sl = (SquareLocation) location;
        int[] x = {-1, 1};
        for (int i : x) {

            int r = (sl.getX()+i) == -1 ? height-1 : sl.getX()+i;
            int c = (sl.getY()+i) == -1 ? width-1 : sl.getX()+i;

            SquareLocation temp = new SquareLocation(r, sl.getY());
            if (validLocation(temp))
                neighbors.add(getCell(temp));

            SquareLocation temp2 = new SquareLocation(sl.getX(), c);
            if (validLocation(temp2))
                neighbors.add(getCell(temp2));
        }

        return neighbors;
    }


}
