package Simulation;

import java.util.ArrayList;

abstract class BasicGrid<E> implements Grid<E> {
//TODO: optional flagging, then decide on method, or new inheritance. Affects subclasses.
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

    private ArrayList<Cell> getNeighbors(Location location, int[] relative) {
        ArrayList<Cell> adjacent = new ArrayList<>();

        SquareLocation sl = (SquareLocation) location;
        //all adjacent
        for (int i : relative) {
            for (int j : relative) {
                if (i == 0 && j == 0)
                    continue;
                SquareLocation temp = new SquareLocation(sl.getX() + i, sl.getY() + j);
                appendNeighbors(temp,adjacent);
            }
        }

        return adjacent;
    }

    private void appendNeighbors(Location location, ArrayList<Cell> neighbors){
        if (validLocation(location))
            neighbors.add(getCell(location));

    }
    public ArrayList<Cell> getAdjacentNeighbors(Location location) {
        int[] relative = {-1, 0, 1};
        return getNeighbors(location, relative);
    }

    public ArrayList<Cell> getCardinalNeighbors(Location location) {
//        int[] relative = {-1, 1};
//        return getNeighbors(location, relative);

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


    public ArrayList getWrappedNeighbors(Location location) {
        ArrayList<Cell> neighbors = new ArrayList<>();

        SquareLocation sl = (SquareLocation) location;
        int[] x = {-1, 1};
        for (int i : x) {

            int r = sl.getX()+i;
            int c = sl.getY()+i;

            if(r == -1) {
                r = height - 1;
            } else if (r == height){
                r = 0;
            }

            if(c == -1) {
                c = width - 1;
            } else if (r == width){
                c = 0;
            }

            SquareLocation temp = new SquareLocation(r, sl.getY());
            appendNeighbors(temp,neighbors);

            SquareLocation temp2 = new SquareLocation(sl.getX(), c);
            appendNeighbors(temp2,neighbors);
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
