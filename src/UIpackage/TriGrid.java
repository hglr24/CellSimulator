package UIpackage;

import javafx.scene.shape.Shape;
import java.util.ArrayList;

public class TriGrid extends ShapeGrid {
    private double myCellSize;
    private static final double H_GAP = 1.3;
    private static final double V_GAP = 1.3;

    public TriGrid(int hsize, int vsize) {
        super(hsize, vsize);
        this.setHgap(H_GAP);
        this.setVgap(V_GAP);
        calcCellSize();
    }

    public void draw(int hsize, int vsize, ArrayList<ArrayList<Shape>> shapes) {

    }

    public void calcCellSize() {

    }
}
