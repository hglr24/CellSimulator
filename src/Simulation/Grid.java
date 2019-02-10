package Simulation;

import java.util.ArrayList;


public interface Grid<E> {

    int getHeight();

    int getWidth();

    int[][] getIntArray(int height, int width);

    boolean validLocation(Location location);

    Cell getCell(Location location);

    ArrayList<Cell> findNeighbors(Location location);

    Cell[][] getCells();

    void update();

    RuleSet getRuleSet();
}
