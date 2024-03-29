package Simulation;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The fundamental type of grid. Lots of common use methods. Holds all of the Cells.
 * @param <E>
 */
abstract public class BasicGrid<E> implements Grid<E> {

    protected Cell[][] cells;
    protected int height;
    protected int width;
    protected RuleSet ruleSet;
    protected Neighborhood neighborhood;
    protected GridType gridType;
    protected HashMap<State,Integer> cellCounts = new HashMap<>();

    /**
     * Instantiate
     * @param height
     * @param width
     * @param neighborhood
     * @param ruleSet
     * @param gridType
     */
    public BasicGrid(int height, int width, Neighborhood neighborhood, RuleSet ruleSet, GridType gridType){
        this.height = height;
        this.width = width;
        this.neighborhood = neighborhood;
        this.gridType = gridType;
        this.ruleSet = ruleSet;

    }

    /**
     * Get the quantity of each cell type currently. Useful for graph display of populations on front end.
     * @return
     */
    public HashMap<Simulation.State,Integer> getCounts(){

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

    /**
     * Ensure location is valid
     * @param location
     * @return
     */
    @Override
    public boolean validLocation(Location location) {
        assert(location instanceof SquareLocation);
        SquareLocation sl = (SquareLocation) location;
        return sl.getX() >= 0 && sl.getY() >= 0 && sl.getX()< height && sl.getY() < width;
    }

    /**
     * iterate through all cells, telling them to update. Serves to step the simulation.
     */
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

    /**
     * Get a specific cell. Errors out if invalid.
     * @param location
     * @return
     */
    @Override
    public Cell getCell(Location location) {
        if (!validLocation(location) || !(location instanceof SquareLocation))
            throw new IllegalArgumentException("Invalid Location");
        SquareLocation sl = (SquareLocation) location;
        return cells[sl.getX()][sl.getY()];
    }

    /**
     * A generic way to find neighbors of a cell, based on its custom Neighborhood
     * @param location
     * @return
     */

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

    @Override
    public int[][] getIntArray(int height, int width) {
        int[][] intArray = new int[height][width];
        for(int k = 0; k < height; k++){
            for(int j = 0; j < width; j++){
                intArray[k][j] = this.cells[k][j].getCurrentState().getValue();
            }
        }
        return intArray;
    }

    /**
     * Get wrapped co-ordinates of locations by translating to their base level position counts
     * @param r
     * @param c
     */
    private void wrapLocation(int r, int c){

        while(r<0)
            r = r+height;
        while(c<0)
            c = c+width;
        while(r>=height)
            r = r-height;
        while(c>=width)
            c = c-width;


    }

    /**
     * add neighbors, with a check for validity 
     * @param location
     * @param neighbors
     */
    protected void appendNeighbors(Location location, ArrayList<Cell> neighbors) {

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
