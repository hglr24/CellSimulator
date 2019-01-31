package UIpackage;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.*;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import java.awt.Dimension;

public class GUIManager {
    private static final Dimension DEFAULT_SIZE = new Dimension(800, 650);
    private Scene myScene;
    private ComboBox<String> simSelector;
    //private Grid myGrid;

    public GUIManager(/*Grid initData*/) {
        //myGrid = initData;
        BorderPane root = new BorderPane();
        myScene = new Scene(root, DEFAULT_SIZE.width, DEFAULT_SIZE.height);
        myScene.setFill(Color.BLACK);

        root.setCenter(initializeGrid());
        root.setBottom(drawControls());
        root.setRight(drawLegend());

    }

    private Node initializeGrid() {
        GridPane grid = new GridPane();

        grid.setStyle("-fx-background-color: rgba(51,102,153,0.44);");
        grid.setHgap(1.3);
        grid.setVgap(1.3);
        grid.setAlignment(Pos.CENTER);
        grid.setMaxSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);

        int hsize = 20; //replace when we have Grid
        int vsize = 20;
        double maxdim = 460;

        double cellsize;
        if (hsize > vsize) cellsize =  maxdim / hsize - grid.getHgap();
        else cellsize = maxdim / vsize - grid.getVgap();

        for (int i = 0; i < hsize; i++) {
            for (int j = 0; j < vsize; j++) {
                Rectangle newShape = new Rectangle();
                newShape.setFill(Color.GRAY);
                newShape.setHeight(cellsize); //set based on cell size in scaled gridpane??
                newShape.setWidth(cellsize);
                grid.add(newShape, i, j);
            }
        }
        return grid;
    }

    private Node drawControls() {
        HBox controls = new HBox();
        controls.setPadding(new Insets(15, 12, 15, 40));
        controls.setSpacing(10);
        controls.setStyle("-fx-background-color: rgba(51,102,153,0.44);");

        Button buttonStart = new Button("Start");
        buttonStart.setPrefSize(100, 20);
        Button buttonStop = new Button("Stop");
        buttonStop.setPrefSize(100, 20);

        Text sLabel = new Text("Simulation Type: ");

        simSelector = new ComboBox<>();
        simSelector.getItems().addAll("Simulation 1", "Simulation 2", "Simulation 3");

        controls.getChildren().addAll(buttonStart, buttonStop, sLabel, simSelector);

        return controls;
    }

    private Node drawLegend() {
        VBox legend = new VBox();
        legend.setPadding(new Insets(10));
        legend.setStyle("-fx-background-color: rgba(51,102,153,0.44);");
        legend.setSpacing(8);
        legend.setPrefHeight(20);

        legend.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, new CornerRadii(2), new BorderWidths(2))));
        Text label = new Text("Legend");
        label.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        legend.getChildren().add(label);
        return legend;
    }

    public Scene getScene() {
        return myScene;
    }
}
