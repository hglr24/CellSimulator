package UIpackage;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

public class GUIManager {
    private static final Dimension DEFAULT_SIZE = new Dimension(800, 650);
    private Scene myScene;
    private ArrayList<ArrayList<Shape>> myShapeGrid;
    private ComboBox<String> simSelector;
    private Button buttonStart;
    private Button buttonStop;
    //private Grid myGrid;
    //private SimulationCoordinator mySim;

    public GUIManager(/*Grid initData, SimulationCoordinator sim*/) {
        //myGrid = initData;
        //mySim = sim;
        BorderPane root = new BorderPane();
        myScene = new Scene(root, DEFAULT_SIZE.width, DEFAULT_SIZE.height);
        root.setCenter(drawInitialGrid());
        root.setBottom(drawControls());
        root.setRight(drawLegend());
        buttonEnable();
        myScene.setOnKeyPressed(e -> keyboardHandler(e.getCode()));
    }

    private Node drawInitialGrid() {
        double maxdim = 460;
        double cellsize;
        //int hsize = myGrid.getWidth();
        //int vsize = myGrid.getHeight();
        int hsize = 20; //replace when we have Grid
        int vsize = 20;

        GridPane grid = new GridPane();
        grid.setStyle("-fx-background-color: rgba(51,102,153,0.44);");
        grid.setHgap(1.3);
        grid.setVgap(1.3);
        grid.setAlignment(Pos.CENTER);
        grid.setMaxSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);

        if (hsize > vsize) cellsize =  maxdim / hsize - grid.getHgap();
        else cellsize = maxdim / vsize - grid.getVgap();

        myShapeGrid = new ArrayList<>(vsize); //initialize ArrayLists that hold shapes
        for (int i = 0; i < vsize; i++) {
            myShapeGrid.add(i, new ArrayList<>(hsize));
        }

        for (int i = 0; i < hsize; i++) {
            for (int j = 0; j < vsize; j++) {
                Rectangle newShape = new Rectangle();
                newShape.setFill(Color.GRAY);
                newShape.setHeight(cellsize);
                newShape.setWidth(cellsize);
                grid.add(newShape, i, j);
                myShapeGrid.get(i).add(j, newShape);
            }
        }
        return grid;
    }

    private void updateCellColor(int row, int col) {
        if (inBounds(row, col)) myShapeGrid.get(row).get(col).setFill(Color.RED);
    }

    private Node drawControls() {
        HBox controls = new HBox();
        controls.setPadding(new Insets(15, 12, 15, 40));
        controls.setSpacing(10);
        controls.setStyle("-fx-background-color: rgba(51,102,153,0.44);");

        buttonStart = drawButton("Start", event -> start());
        buttonStop = drawButton("Stop", event -> stop());

        Text sLabel = new Text("Simulation Type: ");
        //ArrayList<String> simList = mySim.getState().getList();

        ArrayList<String> simList = new ArrayList<>(); //delete later
        simList.add("Simulation 1");                   //|
        simList.add("Simulation 2");                   //|
        simList.add("Simulation 3");                   //to here

        simSelector = drawComboBox("Select...", simList);
        simSelector.valueProperty().addListener((o, o2, selOption) -> changeSim(selOption));

        controls.getChildren().addAll(buttonStart, buttonStop, sLabel, simSelector);
        return controls;
    }

    private Button drawButton(String label, EventHandler<ActionEvent> handler) {
        Button newButton = new Button(label);
        newButton.setPrefSize(100, 20);
        newButton.setOnAction(handler);
        return newButton;
    }

    private ComboBox<String> drawComboBox(String prompt, List options) {
        ComboBox<String> selector = new ComboBox<>();
        selector.setPromptText("Select...");

        selector.getItems().addAll(options);
        return selector;
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

    private void changeSim(String newSim) {
        //mySim.change(newSim);
        System.out.println("Simulation changed: " + newSim);
    }

    private void start() {
        //mySim.start();
        System.out.println("Simulation started.");
    }

    private void stop() {
        //mySim.stop();
        System.out.println("Simulation stopped.");
    }

    private void pause() {
        //mySim.pause();
        System.out.println("Simulation paused.");
    }

    //waiting for Grid object to be made
    /*public void updateGrid(Grid newGrid) {
        buttonEnable();
        myGrid = newGrid;
        for (int i = 0; i < myGrid.getHeight(); i++) {
            for (int j= 0; j < myGrid.getWidth(); j++) {
                Cell curr = myGrid.get2DList().get(i).get(j);
                if (curr.hasChanged()) updateCellColor(i, j);
            }
        }
    }*/

    private void buttonEnable() {
        //buttonStart.setDisable(mySim.hasStarted());
        //buttonStop.setDisable(!mySim.hasStarted());
    }

    private void keyboardHandler(KeyCode code) {
        if (code == KeyCode.ESCAPE) {
            stop();
        }
        if (code == KeyCode.CONTROL) {
            start();
        }
        if (code == KeyCode.SHIFT) {
            pause();
        }
    }

    private void errorBox(String errorType, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(errorType);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private boolean inBounds(int row, int col) {
        //if (row < myGrid.getWidth() && col < myGrid.getHeight()) return true;
        //errorBox("Cell Error", "Attempted to access out-of-bounds cell!");
        //return false;
        return true;
    }

    public Scene getScene() {
        return myScene;
    }
}
