package UIpackage;

import javafx.geometry.Insets;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeType;

import java.util.ArrayList;

public class TriGrid extends ShapeGrid {
    private double myCellRadius;
    private boolean myGridBorder;
    private static final double PAD_H_OFFSET = 1.5;
    private static final double PAD_V_OFFSET = 4.0;
    private static final double HGAP_OFFSET = Math.sqrt(3) / 2 - 1;
    private static final int ROTATE_DEGREES = 180;

    TriGrid(int hsize, int vsize, int cellsize, boolean gridBorder) {
        super(hsize, vsize);
        myCellRadius = cellsize / 2.0;
        myGridBorder = gridBorder;
        this.setHgap(-myCellRadius * HGAP_OFFSET);
        this.setVgap(-1);
        this.setPadding(new Insets(0, super.getHSize() / PAD_H_OFFSET, super.getVSize() / PAD_V_OFFSET, 0));
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
                if (j % 2 == 1) {
                    newShape.setRotate(ROTATE_DEGREES);
                }
                if (i % 2 == 1) {
                    newShape.setRotate(newShape.getRotate() + ROTATE_DEGREES);
                }
                this.add(newShape, j, i);
                shapes.get(i).add(j, newShape);
            }
        }
    }
}
