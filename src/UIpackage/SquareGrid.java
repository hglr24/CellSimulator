package UIpackage;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import java.util.ArrayList;

public class SquareGrid extends ShapeGrid {
    private double myCellSize;
    private static final double H_GAP = 1.3;
    private static final double V_GAP = 1.3;

    public SquareGrid(int hsize, int vsize) {
        super(hsize, vsize);
        this.setHgap(H_GAP);
        this.setVgap(V_GAP);
        calcCellSize();
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

    public void calcCellSize() {
        if (super.getHSize() > super.getVSize()) myCellSize =  super.getHeightMax() / super.getHSize() - this.getHgap();
        else myCellSize = super.getHeightMax() / super.getVSize() - this.getVgap();
    }
}
