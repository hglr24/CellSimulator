package UIpackage;

import Configuration.*;
import Simulation.Grid;
import Configuration.Shape;
import Simulation.SimulationType;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Main class for CellSociety project, initializes and monitors simulation, handles timing, file load/save
 */
public class CellSocietyMain extends Application {
    private ArrayList<Stage> myStages = new ArrayList<>();
    private ArrayList<SimulationType> mySimTypes = new ArrayList<>();
    private ArrayList<Shape> myShapes = new ArrayList<>();
    private ArrayList<GUIManager> myGUIs = new ArrayList<>();
    private ArrayList<SimulationInfo> mySimInfos = new ArrayList<>();
    private ArrayList<Grid> myCurrentGrids = new ArrayList<>();
    private ArrayList<AnimationTimer> myTimers = new ArrayList<>();
    private ArrayList<Double> mySpeeds = new ArrayList<>();
    private ArrayList<Boolean> hasStarted = new ArrayList<>();
    private ArrayList<Boolean> isPaused = new ArrayList<>();
    private static final String TITLE = "CellSociety";
    private static final FileChooser myFileChooser = new FileChooser();
    private static final FileChooser myFileSaver = new FileChooser();
    private static final double SECOND_IN_NANOS = 1000000000;

    /**
     * Starts the simulation given the specified JavaFX stage
     * @param stage Stage to load simulation into
     */
    @Override
    public void start (Stage stage) {
        initializeFileOpener();
        initializeFileSaver();
        File dataFile = new File("data\\TestFire.xml");
        openFile(dataFile, stage);
    }

    private void openFile(File file, Stage stage) {
        myStages.add(stage);
        int newStageIndex = myStages.indexOf(stage);
        try {
            myCurrentGrids.add(newStageIndex, parseXML(file, newStageIndex));
            initializeNewStageProps(stage);
            makeGUI(stage);
        }
        catch (IllegalStateException | IllegalArgumentException | XMLException e) {
            ErrorBox error = new ErrorBox("Load Error", "Invalid XML file");
            error.display();
            myStages.remove(stage);
        }
    }

    private void initializeNewStageProps(Stage stage) {
        int stageIndex = myStages.indexOf(stage);
        hasStarted.add(stageIndex, false);
        isPaused.add(stageIndex, false);
        mySpeeds.add(stageIndex, 1.0);
        initializeTimer(stageIndex);
    }

    private void makeGUI(Stage stage) {
        int stageIndex = myStages.indexOf(stage);
        int cellsize = mySimInfos.get(stageIndex).getCellSize();
        boolean gridBorder = mySimInfos.get(stageIndex).getOutline();
        myGUIs.add(stageIndex, new GUIManager(myCurrentGrids.get(stageIndex),
                this, mySimTypes.get(stageIndex), myShapes.get(stageIndex), stageIndex, cellsize, gridBorder));
        stage.setResizable(false);
        stage.setTitle(TITLE);
        stage.setScene(myGUIs.get(stageIndex).getScene());
        stage.show();
    }

    private Grid parseXML(File dataFile, int newStageIndex) {
        XMLReader testRead = new XMLReader();
        SimulationInfo testSim = testRead.getSimulation(dataFile);
        mySimInfos.add(newStageIndex, testSim);
        Grid gridType;
        if(testSim.getType() != null) {
            mySimTypes.add(newStageIndex, testSim.getType());
            myShapes.add(newStageIndex, testSim.getShape());
            gridType = testSim.getGridType();
        }
        else {
            throw new IllegalStateException();
        }
        return gridType;
    }

    private void initializeTimer(int stageIndex) {
        myTimers.add(stageIndex, new AnimationTimer() {
            private long lastUpdate = 0;

            /**
             * Handles update of simulation based on a timer
             * @param currTime Time elapsed since timer was started
             */
            @Override
            public void handle(long currTime) {
                if (currTime - lastUpdate >= mySpeeds.get(stageIndex) * SECOND_IN_NANOS) {
                    stepSim(stageIndex);
                    lastUpdate = currTime;
                }
            }
        });
    }

    private void initializeFileOpener() {
        myFileChooser.setTitle("Open Simulation XML File");
        myFileChooser.setInitialDirectory(new File(System.getProperty("user.dir") + "/data"));
        myFileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Simulation XML", "*.xml"));
    }

    private void initializeFileSaver() {
        myFileSaver.setTitle("Save simulation state as XML File");
        myFileSaver.setInitialDirectory(new File(System.getProperty("user.dir") + "/data"));
        myFileSaver.getExtensionFilters().add(new FileChooser.ExtensionFilter("Simulation XML", "*.xml"));
    }

    void loadNewSim() throws FileNotFoundException {
        File newFile = myFileChooser.showOpenDialog(new Stage());
        if (newFile != null) {
            Stage newStage = new Stage();
            openFile(newFile, newStage);
        }
        else {
            throw new FileNotFoundException();
        }
    }

    void saveXML(Grid grid, int stageIndex) {
        SimulationInfo simInfo = mySimInfos.get(stageIndex);
        myFileSaver.setInitialFileName("SavedXML" + simInfo.getTitle().trim() + ".xml");
        File saveFile = myFileSaver.showSaveDialog(new Stage());
        if (saveFile != null) {
            XMLWriter xmlWrite = new XMLWriter(simInfo.getSimStrings());
            xmlWrite.writeXML(grid, saveFile);
        }
    }

    void startSim(int stageIndex) {
        hasStarted.set(stageIndex, true);
        isPaused.set(stageIndex, false);
        myTimers.get(stageIndex).start();
    }

    void stepSim(int stageIndex) {
        myCurrentGrids.get(stageIndex).update();
        myGUIs.get(stageIndex).updateGrid(myCurrentGrids.get(stageIndex));
    }

    void pauseSim(int stageIndex) {
        if (isPaused.get(stageIndex)) {
            startSim(stageIndex);
        }
        else {
            isPaused.set(stageIndex, true);
            myTimers.get(stageIndex).stop();
        }
    }

    void editParam(int stageIndex) {
        ParamWindow pw = new ParamWindow(mySimInfos.get(stageIndex));
    }

    void setSpeed(int stageIndex, double newSpeed) {
        mySpeeds.set(stageIndex, 1 / newSpeed);
    }

    double getSpeed(int stageIndex) {
        return mySpeeds.get(stageIndex);
    }

    boolean hasStarted(int stageIndex) {
        return hasStarted.get(stageIndex);
    }

    boolean isPaused(int stageIndex) {
        return isPaused.get(stageIndex);
    }

    /**
     * Start of the program.
     */
    public static void main (String[] args) {
        launch(args);
    }
}