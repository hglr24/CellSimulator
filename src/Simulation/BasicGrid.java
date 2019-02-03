package Simulation;

import java.util.ArrayList;

abstract class BasicGrid<E> implements Grid<E> {

    protected Cell[][] cells;
    protected int height;
    protected int width;
    protected RuleSet ruleSet;

    @Override
    public boolean validLocation(Location location) {
        assert(location instanceof SquareLocation);
        SquareLocation sl = (SquareLocation) location;
        return sl.getX() >= 0 && sl.getY() >= 0 && sl.getX()< getHeight() && sl.getY() < getWidth();
    }

    @Override
    public void update(){
        for(Cell[] r: getCells()){
            for(Cell c: r){
                c.setNeighbors(this);
                c.determineState(this);
            }
        }

        for(Cell[] r: getCells()){
            for(Cell c: r){
                c.goToNextState();
            }
        }
    }


    @Override
    public Cell[][] getCells() {
        return cells;
    }

    @Override
    public Cell getCell(Location location) {
        if (!validLocation(location) || !(location instanceof SquareLocation))
            throw new IllegalArgumentException("Invalid Location");
        SquareLocation sl = (SquareLocation) location;
        return cells[sl.getX()][sl.getY()];
    }

    public ArrayList<Cell> getAdjacentCells(Location location) {
        ArrayList<Cell> adjacent = new ArrayList<>();

        SquareLocation sl = (SquareLocation) location;
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

    public ArrayList<Cell> getCardinalNeighbors(Location location) {
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

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public RuleSet getRuleSet() {
        return ruleSet;
    }

}
