package UIpackage;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import java.util.ArrayList;

public class SquareGrid extends ShapeGrid {
    private double myCellSize;

    SquareGrid(int hsize, int vsize, int cellsize) {
        super(hsize, vsize);
        myCellSize = cellsize;
    }

    public void draw(int hsize, int vsize, ArrayList<ArrayList<Shape>> shapes) {
        for (int i = 0; i < vsize; i++) {
            for (int j = 0; j < hsize; j++) {
                Rectangle newShape = new Rectangle();
                newShape.setFill(Color.GRAY);
                newShape.setHeight(myCellSize);
                newShape.setWidth(myCellSize);
                this.add(newShape, j, i);
                shapes.get(i).add(j, newShape);
            }
        }
    }
}
