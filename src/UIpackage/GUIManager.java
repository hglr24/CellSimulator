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
    private static final int LEGEND_SQUARE_SIZE = 20;
    private static final Insets LEGEND_DATA_INSETS = new Insets(15);
    private static final Insets LEGEND_PANE_INSETS = new Insets(15);
    private static final Insets LEGEND_BOX_INSETS = new Insets(10);
    private static final int LEGEND_DATA_SPACING = 15;
    private static final int LEGEND_PANE_SPACING = 8;
    private static final Border LEGEND_BORDER = new Border(new BorderStroke(Color.BLACK,
            BorderStrokeStyle.SOLID, new CornerRadii(2), new BorderWidths(2)));
    private static final Font TITLE_FONT = Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20);
    private static final int LEGEND_HEIGHT = 20;
    private static final String CONTROLS_COLOR = "-fx-background-color: rgb(110,122,153);";
    private static final Insets CONTROLS_INSETS = new Insets(15, 15, 15, 40);
    private static final int CONTROLS_SPACING = 10;
    private static final int BUTTON_WIDTH = 100;
    private static final int BUTTON_HEIGHT = 20;

    public GUIManager(Grid initData, SimManager sim, SimulationType simtype) {
        myGrid = initData;
        mySim = sim;
        mySimType = simtype;
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
        int hsize = myGrid.getWidth();
        int vsize = myGrid.getHeight();

        ShapeGrid grid = new SquareGrid(hsize, vsize); //Change grid shape for testing here
        grid.setStyle(BACKGROUND_COLOR);
        myShapeGrid = new ArrayList<>(vsize); //initialize ArrayLists that hold shapes
        for (int i = 0; i < vsize; i++) {
            myShapeGrid.add(i, new ArrayList<>(hsize));
        }
        grid.draw(hsize, vsize, myShapeGrid);
        //updateGrid(myGrid); //TODO
        return grid;
    }

    private Node drawControls() {
        HBox controls = new HBox();
        controls.setPadding(CONTROLS_INSETS);
        controls.setSpacing(CONTROLS_SPACING);
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
        newButton.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
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
        title.setFont(TITLE_FONT);
        titleBox.getChildren().add(title);
        myTitlePane.getChildren().add(titleBox);
    }

    private void drawLegend() {
        VBox legend = new VBox();
        legend.setPadding(LEGEND_BOX_INSETS);
        legend.setStyle(LEGEND_COLOR);
        legend.setSpacing(LEGEND_PANE_SPACING);
        legend.setPrefHeight(LEGEND_HEIGHT);
        legend.setBorder(LEGEND_BORDER);

        Text label = new Text("State Legend");
        label.setFont(TITLE_FONT);
        legend.getChildren().add(label);
        populateLegend(legend);
        myLegendPane.setAlignment(Pos.CENTER);
        myLegendPane.setPadding(LEGEND_PANE_INSETS);
        myLegendPane.setStyle(BACKGROUND_COLOR);
        myLegendPane.add(legend, 0, 0);
    }

    private void populateLegend(VBox legend) {
        State[] states = mySimType.getState(); //get each kind of state for simulation mySimType
        List<State> stateList = Arrays.asList(states); //convert states to strings
        ArrayList<String> stateLabels = makeLegendStrings(stateList);
        for (String s : stateLabels) {
            HBox infoRow = new HBox();
            infoRow.setPadding(LEGEND_DATA_INSETS);
            infoRow.setSpacing(LEGEND_DATA_SPACING);
            Rectangle stateColor = new Rectangle(LEGEND_SQUARE_SIZE, LEGEND_SQUARE_SIZE);
            stateColor.setFill(stateList.get(stateLabels.indexOf(s)).getColor());
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

    public void updateGrid(Grid newGrid) {
        buttonEnable();
        myGrid = newGrid;
        for (int i = 0; i < myGrid.getHeight(); i++) {
            for (int j= 0; j < myGrid.getWidth(); j++) {
                Cell curr = myGrid.getCells()[i][j];
                if (curr.hasChanged()) updateCellColor(i, j, curr.getNextState());
            }
        }
    }

    private void updateCellColor(int row, int col, State newState) {
        if (inBounds(row, col)) myShapeGrid.get(row).get(col).setFill(newState.getColor());
    }

    private void changeSim(String newTypeString) {
        for (SimulationType type : simTypeMap.keySet()) {
            if (simTypeMap.get(type).equals(newTypeString)) mySimType = type;
        }
        //mySim.change(mySimType); //reset sim params before gui reset
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

    private void buttonEnable() {
        //buttonStart.setDisable(mySim.hasStarted()); //TODO
        //buttonStop.setDisable(!mySim.hasStarted());
    }

    private void errorBox(String errorType, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(errorType);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private boolean inBounds(int row, int col) {
        if (row < myGrid.getWidth() && col < myGrid.getHeight()) return true;
        errorBox("Cell Error", "Attempted to access out-of-bounds cell!");
        return false;
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

    Scene getScene() {
        return myScene;
    }
}
