package UIpackage;

import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import java.util.ArrayList;

public class TriGrid extends ShapeGrid {
    private double myCellRadius;
    private static final double H_GAP = 1.3;
    private static final double V_GAP = 1.3;

    public TriGrid(int hsize, int vsize) {
        super(hsize, vsize);
        calcCellSize();
        this.setHgap(H_GAP - myCellRadius * Math.sqrt(3) / 2);
        this.setVgap(V_GAP);
    }

    public void draw(int hsize, int vsize, ArrayList<ArrayList<Shape>> shapes) {
        for (int i = 0; i < vsize; i++) {
            for (int j = 0; j < hsize; j++) {
                Triangle newShape = new Triangle(myCellRadius);
                newShape.setFill(Color.GRAY);
                if (j % 2 == 1) newShape.setRotate(180);
                if (i % 2 == 1) newShape.setRotate(newShape.getRotate() + 180);
                this.add(newShape, j, i);
                shapes.get(i).add(j, newShape);
            }
        }
    }

    public void calcCellSize() {
        if ((super.getHSize() / 4) > super.getVSize()) myCellRadius =  super.getHeightMax() / super.getHSize() - this.getHgap();
        else myCellRadius = super.getHeightMax() / super.getVSize() - this.getVgap();
        myCellRadius *= 0.5;
    }
}
