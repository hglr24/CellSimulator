package UIpackage;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.awt.Dimension;

public class GUIManager {
    private static final Dimension DEFAULT_SIZE = new Dimension(800, 800);
    private Scene myScene;
    private ComboBox<String> simSelector;
    //private Grid myGrid;

    public GUIManager(/*Grid initData*/) {
        //myGrid = initData;
        BorderPane root = new BorderPane();

        root.setCenter(initializeGrid());
        root.setBottom(drawControls());
        root.setRight(drawLegend());
        // control the navigation
        enableButtons();

        myScene = new Scene(root, DEFAULT_SIZE.width, DEFAULT_SIZE.height);
    }

    private Node initializeGrid() {
        GridPane grid = new GridPane();
        //output GridPane with myGrid displayed
        return grid;
    }

    private Node drawControls() {
        HBox controls = new HBox();

        return controls;
    }

    private Node drawLegend() {
        VBox legend = new VBox();
        legend.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, new CornerRadii(10), new BorderWidths(10))));
        Label label = new Label("Legend");
        legend.getChildren().add(label);
        return legend;
    }

    private void enableButtons() {

    }

    public Scene getScene() {
        return myScene;
    }
}
