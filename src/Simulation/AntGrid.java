package Simulation;

import java.util.ArrayList;

public class AntGrid extends BasicGrid {

    public AntGrid(int height, int width, int[][] initInts, AntRuleSet ruleSet, Neighborhood neighborhood) {
        super(height,width,neighborhood, ruleSet, GridType.BOUNDED);
        for (State t: AntState.values()) {
            cellCounts.put(t,0);
        }
        AntCell[][] initCells = new AntCell[height][width];
        for (int k = 0; k < height; k++) {
            for (int j = 0; j < width; j++) {
                switch (initInts[k][j]) {
                    case 0:
                        initCells[k][j] = new AntCell(k, j, AntState.EMPTY);
                        break;
                    case 1:
                        initCells[k][j] = new AntCell(k, j, AntState.FOOD);
                        break;
                    case 2:
                        initCells[k][j] = new AntCell(k, j, AntState.HOME);
                        break;
                    case 3:
                        initCells[k][j] = new AntCell(k, j, AntState.ANT_NO_FOOD);
                        break;
                    case 4:
                        initCells[k][j] = new AntCell(k, j, AntState.ANT_WITH_FOOD);
                        break;
                }
            }
        }
        this.cells = initCells;
    }

    public ArrayList<AntCell> findForwardNeighbors(Location location, Heading heading) {
        ArrayList<AntCell> adjacent = new ArrayList<>();

        SquareLocation sl = (SquareLocation) location;
        int[][] deltas = heading.getDeltas();
        for(int i = 0; i < deltas[0].length;i++){
            int r = sl.getX() + deltas[0][i];
            int c = sl.getY() + deltas[1][i];

            SquareLocation temp = new SquareLocation(r,c);
            validNeighbors(temp, adjacent);
        }

        return adjacent;
    }
    private void validNeighbors(Location location, ArrayList<AntCell> neighbors) {

        if (validLocation(location) && ((AntCell) getCell(location)).available())
            neighbors.add((AntCell)getCell(location));
    }
}
