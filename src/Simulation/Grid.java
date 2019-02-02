package Simulation;

import java.util.ArrayList;


public interface Grid<E> {

    int getHeight();

    int getWidth();

    boolean validLocation(Location location);

    Cell getCell(Location location);

    ArrayList<E> findNeighbors(Location location);

    Cell[][] getCells();

    void update();

    RuleSet getRuleSet();
}
