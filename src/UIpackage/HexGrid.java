package UIpackage;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import java.util.ArrayList;

public class HexGrid extends ShapeGrid {
    private double myCellRadius;
    private static final double H_GAP = 1.3;
    private static final double V_GAP = 1.3;
    private ArrayList<GridPane> rowList;

    public HexGrid(int hsize, int vsize) {
        super(hsize, vsize);
        calcCellSize();
        rowList = new ArrayList<>();
        makeHorizGrids(vsize);
        this.setVgap(V_GAP - myCellRadius / 2);
    }

    private void makeHorizGrids(int vsize) {
        for (int i = 0; i < vsize; i++) {
            GridPane rowPane = new GridPane();
            rowPane.setHgap(H_GAP);
            rowList.add(i, rowPane);
            this.add(rowPane, 0, i);
        }
    }

    public void draw(int hsize, int vsize, ArrayList<ArrayList<Shape>> shapes) {
        for (int i = 0; i < vsize; i++) {
            for (int j = 0; j < hsize; j++) {
                Hexagon newShape = new Hexagon(myCellRadius);
                newShape.setFill(Color.GRAY);
                rowList.get(i).add(newShape, j, 0);
                if (i % 2 == 1) {
                    rowList.get(i).setTranslateX(myCellRadius * Math.sqrt(3) / 2);
                }
                shapes.get(i).add(j, newShape);
            }
        }
    }

    public void calcCellSize() {
        if (super.getHSize() > super.getVSize()) myCellRadius =  super.getHeightMax() / super.getHSize() - this.getHgap();
        else myCellRadius = super.getHeightMax() / super.getVSize() - this.getVgap();
        myCellRadius *= 0.5;
    }
}
