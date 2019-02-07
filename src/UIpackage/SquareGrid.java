package UIpackage;

import javafx.geometry.Insets;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeType;

import java.util.ArrayList;

public class SquareGrid extends ShapeGrid {
    private double myCellSize;
    private boolean myGridBorder;

    SquareGrid(int hsize, int vsize, int cellsize, boolean gridBorder) {
        super(hsize, vsize);
        myCellSize = cellsize;
        myGridBorder = gridBorder;
        this.setHgap(-1);
        this.setVgap(-1);
        this.setPadding(new Insets(0, 10, 10, 0));
    }

    public void draw(int hsize, int vsize, ArrayList<ArrayList<Shape>> shapes) {
        for (int i = 0; i < vsize; i++) {
            for (int j = 0; j < hsize; j++) {
                Rectangle newShape = new Rectangle();
                newShape.setFill(Color.GRAY);
                newShape.setHeight(myCellSize);
                newShape.setWidth(myCellSize);
                if (myGridBorder) {
                    newShape.setStroke(Color.BLACK);
                    newShape.setStrokeType(StrokeType.INSIDE);
                }
                this.add(newShape, j, i);
                shapes.get(i).add(j, newShape);
            }
        }
    }
}
