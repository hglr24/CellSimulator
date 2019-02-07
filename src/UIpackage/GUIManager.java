package UIpackage;

import Simulation.Cell;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import Simulation.*;

public class GUIManager {
    private Scene myScene;
    private ArrayList<ArrayList<Shape>> myShapeGrid;
    private Button buttonStart;
    private Button buttonStep;
    private Button buttonPause;
    private Grid myGrid;
    private BorderPane myBorderPane;
    private HBox myTitlePane;
    private CellSocietyMain mySim;
    private SimulationType mySimType;
    private GridPane myLegendPane;
    private String myShape;
    private int myStageID;
    private int myCellSize;
    private static final Dimension DEFAULT_SIZE = new Dimension(800, 650);
    private static final int LEGEND_SQUARE_SIZE = 20;
    private static final int MAX_SIM_SPEED = 10;
    private static final String DEFAULT_STYLESHEET = "/resources/default.css";

    public GUIManager(Grid initData, CellSocietyMain sim, SimulationType simtype, String shape, int stageID, int cellsize) {
        myGrid = initData;
        mySim = sim;
        mySimType = simtype;
        myShape = shape;
        myStageID = stageID;
        myCellSize = cellsize;

        myBorderPane = new BorderPane();
        myScene = new Scene(myBorderPane, DEFAULT_SIZE.width, DEFAULT_SIZE.height);
        myBorderPane.setBottom(drawControls());
        myScene.getStylesheets().add(getClass().getResource(DEFAULT_STYLESHEET).toExternalForm());
        myScene.setOnKeyPressed(e -> keyboardHandler(e.getCode()));
        resetGUI();
    }

    private void resetGUI() {
        System.out.println("GUI Reset");
        initializeShapeArrays();
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

    private void initializeShapeArrays() {
        int hsize = myGrid.getWidth();
        int vsize = myGrid.getHeight();
        myShapeGrid = new ArrayList<>(vsize);
        for (int i = 0; i < vsize; i++) {
            myShapeGrid.add(i, new ArrayList<>(hsize));
        }
    }

    private Node drawInitialGrid() {
        int hsize = myGrid.getWidth();
        int vsize = myGrid.getHeight();
        HBox centerPane = new HBox();
        ScrollPane centerBox = new ScrollPane();
        HBox gridBox = new HBox();
        ShapeGrid grid = makeShapeGrid(hsize, vsize, myCellSize);

        grid.draw(hsize, vsize, myShapeGrid);
        grid.getStyleClass().add("shape-grid");
        gridBox.getStyleClass().addAll("shape-grid", "grid-container");
        centerPane.getStyleClass().add("shape-grid");
        gridBox.getChildren().add(grid);
        centerBox.setContent(gridBox);
        centerPane.getChildren().add(centerBox);
        updateGrid(myGrid);
        return centerPane;
    }

    private ShapeGrid makeShapeGrid(int hsize, int vsize, int cellsize) {
        ShapeGrid grid;
        switch(myShape) {
            case "Square":
                grid = new SquareGrid(hsize, vsize, cellsize);
                break;
            case "Triangle":
                grid = new TriGrid(hsize, vsize, cellsize);
                break;
            case "Hexagon":
                grid = new HexGrid(hsize, vsize, cellsize);
                break;
            default:
                grid = new SquareGrid(hsize, vsize, cellsize);
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

        controls.getChildren().addAll(buttonStart, buttonStop, buttonPause, buttonStep);
        return controls;
    }

    private Button drawButton(String label, EventHandler<ActionEvent> handler) {
        Button newButton = new Button(label);
        newButton.setOnAction(handler);
        return newButton;
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
        slider.setValue(mySim.getSpeed(myStageID));
        slider.setShowTickLabels(true);
        slider.setMajorTickUnit(2);
        slider.setBlockIncrement(1);

        slider.valueProperty().addListener((arg, oldValue, newValue) -> mySim.setSpeed(myStageID, (Double) newValue));
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

    private void start() {
        mySim.startSim(myStageID);
        buttonEnable();
        System.out.println("Simulation " + myStageID + " started");
    }

    private void loadNewSim() {
        mySim.loadNewSim(myStageID);
    }

    private void pause() {
        mySim.pauseSim(myStageID);
        if (buttonPause.getText().equals("Pause")) buttonPause.setText("Resume");
        else buttonPause.setText("Pause");
        buttonEnable();
        System.out.println("Simulation " + myStageID + " paused");
    }

    private void step() {
        mySim.stepSim(myStageID);
        buttonEnable();
        System.out.println("Simulation " + myStageID + " stepped");
    }

    private void buttonEnable() {
        buttonStart.setDisable(mySim.hasStarted(myStageID));
        buttonStep.setDisable(mySim.hasStarted(myStageID) && !mySim.isPaused(myStageID));
        buttonPause.setDisable(!mySim.hasStarted(myStageID));
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