package Simulation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

abstract class BasicGrid<E> implements Grid<E> {
    //TODO: add accessibility to RuleSet
    //TODO: make smart ENUM for all kinds of neighbor types, have interface. have row/col pairings
//TODO: make get/set better

    protected Cell[][] cells;
    protected int height;
    protected int width;
    protected RuleSet ruleSet;
    protected Neighborhood neighborhood;
    protected GridType gridType;
    protected  Map<State,Integer> cellCounts = new HashMap<>();

    public BasicGrid(int height, int width, Neighborhood neighborhood, RuleSet ruleSet, GridType gridType){
        this.height = height;
        this.width = width;
        this.neighborhood = neighborhood;
        this.gridType = gridType;
        this.ruleSet = ruleSet;

    }
    public Map<State,Integer> getCounts(){

        for (State key : cellCounts.keySet()) {
            cellCounts.put(key,0);
        }

        for(Cell[] r: cells){
            for(Cell c: r){
               cellCounts.put(c.getCurrentState(),cellCounts.get(c.getCurrentState())+1);
            }
        }

        return cellCounts;
    }


    @Override
    public boolean validLocation(Location location) {
        assert(location instanceof SquareLocation);
        SquareLocation sl = (SquareLocation) location;
        return sl.getX() >= 0 && sl.getY() >= 0 && sl.getX()< getHeight() && sl.getY() < getWidth();
    }

    @Override
    public void update(){
        for(Cell[] r: cells){
            for(Cell c: r){
                c.setNeighbors(this);
                c.determineState(this);
            }
        }

        for(Cell[] r: cells){
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
    @Override
    public ArrayList<Cell> findNeighbors(Location location) {
        ArrayList<Cell> adjacent = new ArrayList<>();

        SquareLocation sl = (SquareLocation) location;
        int[][] deltas = neighborhood.getDeltas();
        for(int i = 0; i < deltas[0].length;i++){
            int r = sl.getX() + deltas[0][i];
            int c = sl.getY() + deltas[1][i];

            switch (gridType){
                case BOUNDED:
                    break;
                case TOROIDAL:
                    wrapLocation(r,c);
                    break;
            }
            SquareLocation temp = new SquareLocation(r,c);
            appendNeighbors(temp,adjacent);
        }

        return adjacent;
    }

    private void wrapLocation(int r, int c){

        while(r<0)
            r = r+height;
        while(c<0)
            c = c+width;
        while(r>0)
            r = r-height;
        while(c>0)
            c = c-width;


    }
    private void appendNeighbors(Location location, ArrayList<Cell> neighbors) {

        if (validLocation(location))
            neighbors.add(getCell(location));
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
