package UIpackage;

import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.shape.Shape;
import java.util.ArrayList;

abstract class ShapeGrid extends GridPane {
    private int myHSize;
    private int myVSize;
    private static final double MAX_HEIGHT = 460;

    public ShapeGrid(int hsize, int vsize) {
        this.myHSize = hsize;
        this.myVSize = vsize;
        this.setAlignment(Pos.CENTER);
        this.setMaxSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
    }

    abstract void draw(int hsize, int vsize, ArrayList<ArrayList<Shape>> shapes);

    abstract void calcCellSize();

    public int getHSize() {
        return myHSize;
    }

    public int getVSize() {
        return myVSize;
    }

    public double getHeightMax() {
        return MAX_HEIGHT;
    }
}
