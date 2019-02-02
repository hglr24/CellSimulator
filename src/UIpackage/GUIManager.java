package UIpackage;

import Simulation.State;
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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import Simulation.*;

public class GUIManager {
    private static final Dimension DEFAULT_SIZE = new Dimension(800, 650);
    private Scene myScene;
    private ArrayList<ArrayList<Shape>> myShapeGrid;
    private ComboBox<String> simSelector;
    private Button buttonStart;
    private Button buttonStop;
    private HashMap<SimulationType, String> simTypeMap;
    private Grid myGrid;
    private BorderPane myBorderPane;
    private HBox myTitlePane;
    private SimManager mySim;
    private SimulationType mySimType;
    private GridPane myLegendPane;
    private static final String BACKGROUND_COLOR = "-fx-background-color: rgb(170,189,206);";
    private static final String LEGEND_COLOR = "-fx-background-color: rgb(96,153,183);";
    private static final String CONTROLS_COLOR = "-fx-background-color: rgb(110,122,153);";

    public GUIManager(/*Grid initData, SimulationCoordinator sim, SimulationType simtype*/) {
        //myGrid = initData; //TODO
        //mySim = sim;
        mySimType = SimulationType.SEGREGATION; //TODO this is for testing
        myBorderPane = new BorderPane();
        myScene = new Scene(myBorderPane, DEFAULT_SIZE.width, DEFAULT_SIZE.height);
        createSimTypeMap();
        myBorderPane.setBottom(drawControls());
        myScene.setOnKeyPressed(e -> keyboardHandler(e.getCode()));
        resetGUI();
    }

    private void resetGUI() {
        System.out.println("GUI Reset");
        myBorderPane.setCenter(drawInitialGrid());
        myTitlePane = new HBox();
        myLegendPane = new GridPane();
        myBorderPane.setRight(myLegendPane);
        myBorderPane.setTop(myTitlePane);
        drawLegend();
        drawTitle();
        buttonEnable();
    }

    private void createSimTypeMap() { //Maps keys (SimulationTypes) to values (String representations)
        simTypeMap = new HashMap<>();
        List<SimulationType> simList = Arrays.asList(SimulationType.values());
        for (SimulationType type : simList) {
            simTypeMap.put(type, type.toString());
        }
    }

    private Node drawInitialGrid() {
        //int hsize = myGrid.getWidth(); //TODO
        //int vsize = myGrid.getHeight();
        int hsize = 20; //replace when we have Grid
        int vsize = 20;

        ShapeGrid grid = new TriGrid(hsize, vsize);
        grid.setStyle(BACKGROUND_COLOR);
        myShapeGrid = new ArrayList<>(vsize); //initialize ArrayLists that hold shapes
        for (int i = 0; i < vsize; i++) {
            myShapeGrid.add(i, new ArrayList<>(hsize));
        }
        grid.draw(hsize, vsize, myShapeGrid);
        //updateGrid(myGrid); //TODO
        return grid;
    }

    private void updateCellColor(int row, int col/*, State newState*/) { //TODO
        if (inBounds(row, col)) myShapeGrid.get(row).get(col).setFill(Color.RED);
    }

    private Node drawControls() {
        HBox controls = new HBox();
        controls.setPadding(new Insets(15, 12, 15, 40));
        controls.setSpacing(10);
        controls.setStyle(CONTROLS_COLOR);

        buttonStart = drawButton("Start", event -> start());
        buttonStop = drawButton("Stop", event -> stop());
        Text sLabel = new Text("Simulation Type: ");

        ArrayList<String> simTypeStrings = new ArrayList<>(simTypeMap.values());
        simSelector = drawComboBox("Select...", simTypeStrings);
        simSelector.valueProperty().addListener((options, oldValue, newValue) -> changeSim(newValue));

        controls.getChildren().addAll(buttonStart, buttonStop, sLabel, simSelector);
        return controls;
    }

    private Button drawButton(String label, EventHandler<ActionEvent> handler) {
        Button newButton = new Button(label);
        newButton.setPrefSize(100, 20);
        newButton.setOnAction(handler);
        return newButton;
    }

    private ComboBox<String> drawComboBox(String prompt, List<String> options) {
        ComboBox<String> selector = new ComboBox<>();
        selector.setPromptText(prompt);
        selector.getItems().addAll(options);
        return selector;
    }

    private void drawTitle() {
        myTitlePane.setAlignment(Pos.CENTER);
        myTitlePane.setStyle(CONTROLS_COLOR);
        HBox titleBox = new HBox();
        Text title = new Text(mySimType.toString());
        title.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        titleBox.getChildren().add(title);
        myTitlePane.getChildren().add(titleBox);
    }

    private void drawLegend() {
        VBox legend = new VBox();
        legend.setPadding(new Insets(10));
        legend.setStyle(LEGEND_COLOR);
        legend.setSpacing(8);
        legend.setPrefHeight(20);
        legend.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, new CornerRadii(2), new BorderWidths(2))));

        Text label = new Text("State Legend");
        label.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        legend.getChildren().add(label);
        populateLegend(legend);
        myLegendPane.setAlignment(Pos.CENTER);
        myLegendPane.setPadding(new Insets(15));
        myLegendPane.setStyle(BACKGROUND_COLOR);
        myLegendPane.add(legend, 0, 0);
    }

    private void populateLegend(VBox legend) {
        State[] states = mySimType.getState(); //get each kind of state for simulation mySimType
        List<State> stateList = Arrays.asList(states); //convert states to strings
        ArrayList<String> stateLabels = makeLegendStrings(stateList);
        for (String s : stateLabels) {
            HBox infoRow = new HBox();
            infoRow.setPadding(new Insets(15));
            infoRow.setSpacing(15);
            Rectangle stateColor = new Rectangle(20, 20);
            stateColor.setFill(stateList.get(stateLabels.indexOf(s)).getColor()); //TODO get real colors
            infoRow.getChildren().addAll(stateColor, new Text(s));
            legend.getChildren().add(infoRow);
        }
    }

    private ArrayList<String> makeLegendStrings(List<State> list) {
        ArrayList<String> rtn = new ArrayList<>();
        for (State state : list) {
            rtn.add(state.toString());
        }
        return rtn;
    }

    private void changeSim(String newTypeString) {
        for (SimulationType type : simTypeMap.keySet()) {
            if (simTypeMap.get(type).equals(newTypeString)) mySimType = type;
        }
        //mySim.change(mySimType);
        resetGUI();
        System.out.println("Simulation changed: " + newTypeString);
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

    //waiting for Grid object to be made //TODO
    /*public void updateGrid(Grid newGrid) {
        buttonEnable();
        myGrid = newGrid;
        for (int i = 0; i < myGrid.getHeight(); i++) {
            for (int j= 0; j < myGrid.getWidth(); j++) {
                Cell curr = myGrid.getCells().get(i).get(j);
                if (curr.hasChanged()) updateCellColor(i, j, curr.getNewState());
            }
        }
    }*/

    private void buttonEnable() {
        //buttonStart.setDisable(mySim.hasStarted()); //TODO
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
        //if (row < myGrid.getWidth() && col < myGrid.getHeight()) return true; //TODO
        //errorBox("Cell Error", "Attempted to access out-of-bounds cell!");
        //return false;
        return true;
    }

    Scene getScene() {
        return myScene;
    }
}
