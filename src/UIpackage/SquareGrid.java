package UIpackage;

import Simulation.Grid;
import Simulation.SimulationType;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeType;
import java.util.ArrayList;

/**
 * Creates square grid with given parameters
 */
public class SquareGrid extends ShapeGrid {
    private double myCellSize;
    private boolean myGridBorder;
    private static final double PAD_OFFSET = 10;

    SquareGrid(int hsize, int vsize, int cellsize, boolean gridBorder, Grid grid, SimulationType type) {
        super(hsize, vsize, type, grid);
        myCellSize = cellsize;
        myGridBorder = gridBorder;
        this.setHgap(-1);
        this.setVgap(-1);
        this.setPadding(new Insets(0, PAD_OFFSET, PAD_OFFSET, 0));
    }

    /**
     * Draws each square in grid
     * @param hsize Number of columns in grid
     * @param vsize Number of rows in grid
     * @param shapes Shape grid passed from GUIManager
     */
    public void draw(int hsize, int vsize, ArrayList<ArrayList<Shape>> shapes) {
        for (int i = 0; i < vsize; i++) {
            for (int j = 0; j < hsize; j++) {
                GridRectangle newShape = new GridRectangle(i, j, myCellSize);
                newShape.setOnMouseClicked(event -> changeState(newShape));
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