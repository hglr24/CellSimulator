package UIpackage;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import java.util.ArrayList;

public class HexGrid extends ShapeGrid {
    private double myCellRadius;
    private ArrayList<GridPane> rowList;

    HexGrid(int hsize, int vsize, int cellsize) {
        super(hsize, vsize);
        myCellRadius = cellsize / 2.0;
        rowList = new ArrayList<>();
        makeHorizGrids(vsize);
        this.setVgap(-myCellRadius / 2 - 1);
    }

    private void makeHorizGrids(int vsize) {
        for (int i = 0; i < vsize; i++) {
            GridPane rowPane = new GridPane();
            rowList.add(i, rowPane);
            rowPane.setHgap(-1);
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
}
