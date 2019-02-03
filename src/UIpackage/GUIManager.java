package UIpackage;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.control.Button;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
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
    private Scene myScene;
    private ArrayList<ArrayList<Shape>> myShapeGrid;
    private Button buttonStart;
    private Button buttonStep;
    private Button buttonPause;
    private HashMap<SimulationType, String> mySimTypeMap;
    private Grid myGrid;
    private BorderPane myBorderPane;
    private HBox myTitlePane;
    private CellSocietyMain mySim;
    private SimulationType mySimType;
    private GridPane myLegendPane;
    private String myShape;
    private static final Dimension DEFAULT_SIZE = new Dimension(800, 650);
    private static final int LEGEND_SQUARE_SIZE = 20;
    private static final int MAX_SIM_SPEED = 10;
    private static final String DEFAULT_STYLESHEET = "/resources/default.css";

    public GUIManager(Grid initData, CellSocietyMain sim, SimulationType simtype, String shape) {
        myGrid = initData;
        mySim = sim;
        mySimType = simtype;
        myShape = shape;
        createSimTypeMap();

        myBorderPane = new BorderPane();
        myScene = new Scene(myBorderPane, DEFAULT_SIZE.width, DEFAULT_SIZE.height);
        myBorderPane.setBottom(drawControls());
        myScene.getStylesheets().add(getClass().getResource(DEFAULT_STYLESHEET).toExternalForm());
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
        drawSlider();
        drawTitle();
        buttonEnable();
    }

    private void createSimTypeMap() { //Maps keys (SimulationTypes) to values (String representations)
        mySimTypeMap = new HashMap<>();
    for (SimulationType type : SimulationType.values()) {
            mySimTypeMap.put(type, type.toString());
        }
    }

    private Node drawInitialGrid() {
        int hsize = myGrid.getWidth();
        int vsize = myGrid.getHeight();

        ShapeGrid grid = makeShapeGrid(hsize, vsize);

        myShapeGrid = new ArrayList<>(vsize);
        for (int i = 0; i < vsize; i++) {
            myShapeGrid.add(i, new ArrayList<>(hsize));
        }
        grid.draw(hsize, vsize, myShapeGrid);
        updateGrid(myGrid);
        return grid;
    }

    private ShapeGrid makeShapeGrid(int hsize, int vsize) {
        ShapeGrid grid;
        switch(myShape) {
            case "Square":
                grid = new SquareGrid(hsize, vsize);
                break;
            case "Triangle":
                grid = new TriGrid(hsize, vsize);
                break;
            case "Hexagon":
                grid = new HexGrid(hsize, vsize);
                break;
            default:
                grid = new SquareGrid(hsize, vsize);
                break;
        }
        return grid;
    }

    private Node drawControls() {
        HBox controls = new HBox();
        controls.getStyleClass().add("controls");

        buttonStart = drawButton("Start", event -> start());
        Button buttonStop = drawButton("Load", event -> loadNewSim());
        buttonStep = drawButton("Step", event -> step());
        buttonPause = drawButton("Pause", event -> pause());
        Text sLabel = new Text("Simulation Type: ");

        ArrayList<String> simTypeStrings = new ArrayList<>(mySimTypeMap.values());
        ComboBox<String> simSelector = drawComboBox("Select...", simTypeStrings);
        simSelector.valueProperty().addListener((options, oldValue, newValue) -> changeSim(newValue));

        controls.getChildren().addAll(buttonStart, buttonStop, buttonPause, buttonStep);
        //controls.getChildren().addAll(sLabel, simSelector);
        return controls;
    }

    private Button drawButton(String label, EventHandler<ActionEvent> handler) {
        Button newButton = new Button(label);
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
        myTitlePane.getStyleClass().add("title");
        HBox titleBox = new HBox();
        Text title = new Text(mySimType.toString());
        titleBox.getChildren().add(title);
        myTitlePane.getChildren().add(titleBox);
    }

    private void drawLegend() {
        VBox legend = newLegendBox("State Legend");
        myLegendPane.getStyleClass().add("legend");
        populateLegend(legend);
        myLegendPane.add(legend, 0, 0);
    }

    private void populateLegend(VBox legend) {
        State[] states = mySimType.getState(); //get each kind of state for simulation mySimType
        List<State> stateList = new ArrayList<>();
        if(states != null) stateList = Arrays.asList(states); //convert states to strings
        ArrayList<String> stateLabels = makeLegendStrings(stateList);
        for (String s : stateLabels) {
            HBox infoRow = new HBox();
            infoRow.getStyleClass().add("legend-item");
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

    private VBox newLegendBox(String title) {
        VBox newBox = new VBox();
        newBox.getStyleClass().add("right-pane-item");

        Text label = new Text(title);
        newBox.getChildren().add(label);
        return newBox;
    }

    private void drawSlider() {
        VBox sliderBox = newLegendBox("Speed");
        Slider slider = new Slider();
        slider.setMin(0);
        slider.setMax(MAX_SIM_SPEED);
        slider.setValue(mySim.getSpeed());
        slider.setShowTickLabels(true);
        slider.setMajorTickUnit(2);
        slider.setBlockIncrement(1);

        slider.valueProperty().addListener((arg, oldValue, newValue) -> mySim.setSpeed((Double) newValue));
        sliderBox.getChildren().add(slider);
        myLegendPane.add(sliderBox, 0, 1);
    }

    void updateGrid(Grid newGrid) {
        myGrid = newGrid;
        for (int i = 0; i < myGrid.getHeight(); i++) {
            for (int j= 0; j < myGrid.getWidth(); j++) {
                Cell curr = myGrid.getCells()[i][j];
                updateCellColor(i, j, curr.getNextState());
            }
        }
    }

    private void updateCellColor(int row, int col, State newState) {
        if (inBounds(row, col)) myShapeGrid.get(row).get(col).setFill(newState.getColor());
    }

    private void changeSim(String newTypeString) {
        for (SimulationType type : mySimTypeMap.keySet()) {
            if (mySimTypeMap.get(type).equals(newTypeString)) mySimType = type;
        }
        //mySim.changeSim(mySimType); //reset sim params before gui reset  //TODO once we have multiple sims in single XML
        resetGUI();
        System.out.println("Simulation changed: " + newTypeString);
    }

    private void start() {
        mySim.startSim();
        buttonEnable();
        System.out.println("Simulation started");
    }

    private void loadNewSim() {
        mySim.loadNewSim();
        buttonEnable();
        System.out.println("Simulation stopped");
    }

    private void pause() {
        mySim.pauseSim();
        if (buttonPause.getText().equals("Pause")) buttonPause.setText("Resume");
        else buttonPause.setText("Pause");
        buttonEnable();
        System.out.println("Simulation paused");
    }

    private void step() {
        mySim.stepSim();
        buttonEnable();
        System.out.println("Simulation stepped");
    }

    private void buttonEnable() {
        buttonStart.setDisable(mySim.hasStarted());
        buttonStep.setDisable(mySim.hasStarted() && !mySim.isPaused());
        buttonPause.setDisable(!mySim.hasStarted());
    }

    void errorBox(String errorType, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(errorType);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private boolean inBounds(int row, int col) {
        if (col < myGrid.getWidth() && row < myGrid.getHeight()) return true;
        errorBox("Cell Error", "Attempted to access out-of-bounds cell!");
        return false;
    }

    private void keyboardHandler(KeyCode code) {
        switch (code) {
            case ESCAPE:
                loadNewSim();
                break;
            case CONTROL:
                start();
                break;
            case SHIFT:
                pause();
                break;
            case S:
                step();
                break;
        }
    }

    Scene getScene() {
        return myScene;
    }
}