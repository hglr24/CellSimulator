package UIpackage;

import Simulation.*;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * This will be implemented into SimulationCoordinator later, bare-bones implementation here for testing
 */
public class GUIMain extends Application {
    private static final String TITLE = "CellSociety";

    @Override
    public void start (Stage stage) {
        SimulationType simTestType = SimulationType.SEGREGATION;
        SimManager simTest = new SimManager();

        int [][] testArray = new int[][] {
                {0, 1, 2, 0, 1},
                {2, 1, 0, 1, 1},
                {0, 0, 0, 0, 0},
                {1, 1, 1, 1, 1},
                {2, 2, 2, 2, 1}
        };

        SegregationGrid testGrid = new SegregationGrid(5, 5, testArray, new SegregationRuleSet(new double[] {1.0})); //TODO this is for testing
        GUIManager display = new GUIManager(testGrid, simTest, simTestType);
        stage.setResizable(false);
        stage.setTitle(TITLE);
        stage.setScene(display.getScene());
        stage.show();
    }

    /**
     * Start of the program.
     */
    public static void main (String[] args) {
        launch(args);
    }
}
