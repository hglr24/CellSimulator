package Simulation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public interface Grid<E> {


    int getHeight();

    int getWidth();

    boolean validLocation(Location location);

    Cell getCell(Location location);

    List<E> getNeighbors(Location location);

    E getCells();
}
