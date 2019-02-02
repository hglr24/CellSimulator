package Simulation;

import java.util.ArrayList;

public class FireGrid extends BasicGrid {

    public FireGrid(int height, int width, int[][] initInts, FireRuleSet ruleSet) {
        this.height = height;
        this.width = width;
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
        this.ruleSet = ruleSet;
    }

    @Override
    public ArrayList findNeighbors(Location location) {
        ArrayList<Cell> neighbors = new ArrayList<>();

        SquareLocation sl = (SquareLocation) location;
        int[] x = {-1, 1};
        for (int i : x) {

            SquareLocation temp = new SquareLocation(sl.getX() + i, sl.getY());
            if (validLocation(temp))
                neighbors.add(getCell(temp));

            SquareLocation temp2 = new SquareLocation(sl.getX(), sl.getY() + i);
            if (validLocation(temp2))
                neighbors.add(getCell(temp2));
        }

        return neighbors;
    }

}
