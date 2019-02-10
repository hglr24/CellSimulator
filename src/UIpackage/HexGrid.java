package UIpackage;

import Simulation.Grid;
import Simulation.SimulationType;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeType;

import java.util.ArrayList;

public class HexGrid extends ShapeGrid {
    private double myCellRadius;
    private boolean myGridBorder;
    private ArrayList<GridPane> rowList;
    private static final double PAD_H_OFFSET = 1.5;
    private static final double PAD_V_OFFSET = 8.0;

    HexGrid(int hsize, int vsize, int cellsize, boolean gridBorder, Grid grid, SimulationType type) {
        super(hsize, vsize, type, grid);
        myCellRadius = cellsize / 2.0;
        rowList = new ArrayList<>();
        myGridBorder = gridBorder;
        makeHorizGrids(vsize);
        this.setVgap(-myCellRadius / 2 - 1);
        this.setPadding(new Insets(0, super.getHSize() / PAD_H_OFFSET, super.getVSize() / PAD_V_OFFSET, 0));
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
                GridHexagon newShape = new GridHexagon(i, j, myCellRadius);
                newShape.setOnMouseClicked(event -> changeState(newShape));
                if (myGridBorder) {
                    newShape.setStroke(Color.BLACK);
                    newShape.setStrokeType(StrokeType.INSIDE);
                }
                rowList.get(i).add(newShape, j, 0);
                if (i % 2 == 1) {
                    rowList.get(i).setTranslateX(myCellRadius * Math.sqrt(3) / 2);
                }
                shapes.get(i).add(j, newShape);
            }
        }
    }
}