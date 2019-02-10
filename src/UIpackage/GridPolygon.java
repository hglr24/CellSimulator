package UIpackage;

import javafx.scene.shape.Polygon;

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
