package UIpackage;

import javafx.application.Application;
import javafx.stage.Stage;

public class GUIMain extends Application {
    public static final String TITLE = "CellSociety";

    @Override
    public void start (Stage stage) {
        GUIManager display = new GUIManager(/*new Grid()*/);
        stage.setTitle(TITLE);
        // add our user interface components to Frame and show it
        stage.setScene(display.getScene());
        stage.show();
        // start somewhere, less typing for debugging
    }

    /**
     * Start of the program.
     */
    public static void main (String[] args) {
        launch(args);
    }
}
