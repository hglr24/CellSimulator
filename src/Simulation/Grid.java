package Simulation;

import java.util.ArrayList;

/**
 * The Grid contract, which serves as the central base of given simulation
 * @param <E>
 */
public interface Grid<E> {

    int getHeight();

    int getWidth();

    /**
     * Used to extract integer representations of the simulation so that it may be stored in XML
     * @param height
     * @param width
     * @return
     */
    int[][] getIntArray(int height, int width);

    /**
     * Check if a cell location is valid. Useful when looking for cell neighbors
     * @param location
     * @return
     */
    boolean validLocation(Location location);

    /**
     * Get a particular cell. Needed by certain cells based on rules, or the front-end for working with specific cell displays
     * @param location
     * @return
     */
    Cell getCell(Location location);

    /**
     * generic way to get neighbors, based on a specific location
     * @param location
     * @return
     */
    ArrayList<Cell> findNeighbors(Location location);

    /**
     * get the entire grid of cells, needed by front-end for display
     * @return
     */
    Cell[][] getCells();

    /**
     * Tell the simulation to update, or iterate
     */
    void update();

    /**
     * get this simulation's ruleset, so it can be read and altered.
     * @return
     */
    RuleSet getRuleSet();
}
