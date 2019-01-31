package UIpackage;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * This will be implemented into SimulationCoordinator later, bare-bones implementation here for testing
 */
public class GUIMain extends Application {
    private static final String TITLE = "CellSociety";

    @Override
    public void start (Stage stage) {
        GUIManager display = new GUIManager(/*myGrid (grid contained in SimulationCoordinator)*/);
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
