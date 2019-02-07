package Simulation;

import java.util.ArrayList;

abstract class HexGrid<E> extends BasicGrid<E> {

    protected Cell[][] cells;
    protected int height;
    protected int width;
    protected RuleSet ruleSet;

@Override
    public ArrayList<Cell> getAdjacentNeighbors(Location location) {
        ArrayList<Cell> adjacent = new ArrayList<>();

        SquareLocation sl = (SquareLocation) location;
        //TODO: edit for hex instead
        //all adjacent
        int[] x = {-1, 0, 1};
        for (int i : x) {
            for (int j : x) {
                if (i == 0 && j == 0)
                    continue;
                SquareLocation temp = new SquareLocation(sl.getX() + i, sl.getY() + j);
                if (validLocation(temp))
                    adjacent.add(getCell(temp));
            }
        }

        return adjacent;
    }

    @Override
    public ArrayList<Cell> getCardinalNeighbors(Location location) {
        return getAdjacentNeighbors(location);
    }

    @Override
    public ArrayList getWrappedNeighbors(Location location) {
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
