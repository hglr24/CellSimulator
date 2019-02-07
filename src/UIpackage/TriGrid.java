package UIpackage;

import javafx.geometry.Insets;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeType;

import java.util.ArrayList;

public class TriGrid extends ShapeGrid {
    private double myCellRadius;
    private boolean myGridBorder;

    TriGrid(int hsize, int vsize, int cellsize, boolean gridBorder) {
        super(hsize, vsize);
        myCellRadius = cellsize / 2.0;
        myGridBorder = gridBorder;
        this.setHgap(-myCellRadius * Math.sqrt(3) / 2 - 1);
        this.setVgap(-1);
        this.setPadding(new Insets(0, super.getHSize() / 1.5, super.getVSize() / 4.0, 0));
    }

    public void draw(int hsize, int vsize, ArrayList<ArrayList<Shape>> shapes) {
        for (int i = 0; i < vsize; i++) {
            for (int j = 0; j < hsize; j++) {
                Triangle newShape = new Triangle(myCellRadius);
                if (myGridBorder) {
                    newShape.setStroke(Color.BLACK);
                    newShape.setStrokeType(StrokeType.INSIDE);
                }
                newShape.setFill(Color.GRAY);
                if (j % 2 == 1) newShape.setRotate(180);
                if (i % 2 == 1) newShape.setRotate(newShape.getRotate() + 180);
                this.add(newShape, j, i);
                shapes.get(i).add(j, newShape);
            }
        }
    }
}
