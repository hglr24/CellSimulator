package UIpackage;

import Simulation.Grid;
import Simulation.SimulationType;
import Simulation.State;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Shape;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Creates grid of abstract shapes with given grid and simulation type
 */
abstract class ShapeGrid extends GridPane {
    private int myHSize;
    private int myVSize;
    private SimulationType mySimType;
    private List<State> myStateList;
    private Grid myGrid;

    ShapeGrid(int hsize, int vsize, SimulationType type, Grid grid) {
        this.myHSize = hsize;
        this.myVSize = vsize;
        this.mySimType = type;
        this.myGrid = grid;
        makeStateList();
    }

    abstract void draw(int hsize, int vsize, ArrayList<ArrayList<Shape>> shapes);

    private void makeStateList() {
        State[] states = mySimType.getState();
        myStateList = new ArrayList<>();
        if(states != null) {
            myStateList = Arrays.asList(states);
        }
    }

    void changeState(GridPolygon shape) {
        State currState = myGrid.getCells()[shape.getRow()][shape.getCol()].getCurrentState();
        State newState;
        if (myStateList.indexOf(currState) == myStateList.size() - 1) {
            newState = myStateList.get(0);
        }
        else {
            newState = myStateList.get(myStateList.indexOf(currState) + 1);
        }
        shape.setFill(newState.getColor());
        myGrid.getCells()[shape.getRow()][shape.getCol()].setClickState(newState);
    }

    int getHSize() {
        return myHSize;
    }

    int getVSize() {
        return myVSize;
    }
}
