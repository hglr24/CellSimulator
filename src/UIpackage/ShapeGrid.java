package UIpackage;

import javafx.scene.layout.GridPane;
import javafx.scene.shape.Shape;
import java.util.ArrayList;

abstract class ShapeGrid extends GridPane {
    private int myHSize;
    private int myVSize;

    public ShapeGrid(int hsize, int vsize) {
        this.myHSize = hsize;
        this.myVSize = vsize;
    }

    abstract void draw(int hsize, int vsize, ArrayList<ArrayList<Shape>> shapes);

    int getHSize() {
        return myHSize;
    }

    int getVSize() {
        return myVSize;
    }
}
