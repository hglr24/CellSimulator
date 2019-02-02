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
        GameOfLifeGrid testGrid = new GameOfLifeGrid(20, 20, new GameOfLifeCell[20][20], new GameOfLifeRuleSet()); //TODO this is for testing
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
