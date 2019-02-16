package UIpackage;

import javafx.scene.shape.Polygon;

/**
 * Creates a polygon to be displayed in the simulation grid with specified coordinates and size
 */
abstract class GridPolygon extends Polygon {
    private int myRow;
    private int myCol;
    private double mySize;

    GridPolygon(int i, int j, double size) {
        myRow = i;
        myCol = j;
        mySize = size;
        setCoords();
    }

    abstract void setCoords();

    int getRow() {
        return myRow;
    }

    int getCol() {
        return myCol;
    }

    double getSize() {
        return mySize;
    }
}
