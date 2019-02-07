package UIpackage;

import Configuration.*;
import Simulation.*;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.util.ArrayList;

public class CellSocietyMain extends Application {
    private ArrayList<Stage> myStages = new ArrayList<>();
    private ArrayList<SimulationType> mySimTypes = new ArrayList<>();
    private ArrayList<String> myShapes = new ArrayList<>();
    private ArrayList<GUIManager> myGUIs = new ArrayList<>();
    private ArrayList<Grid> myCurrentGrids = new ArrayList<>();
    private ArrayList<AnimationTimer> myTimers = new ArrayList<>();
    private ArrayList<Double> mySpeeds = new ArrayList<>();
    private ArrayList<Boolean> hasStarted = new ArrayList<>();
    private ArrayList<Boolean> isPaused = new ArrayList<>();
    private static final String TITLE = "CellSociety";
    private static final FileChooser myFileChooser = new FileChooser();

    @Override
    public void start (Stage stage) {
        initializeFileOpener();

        File dataFile = new File("data\\TestSegregation.xml");
        openFile(dataFile, stage, 0);
    }

    private void openFile(File file, Stage stage, int oldStageIndex) {
        myStages.add(stage);
        int newStageIndex = myStages.indexOf(stage);
        myCurrentGrids.add(newStageIndex, parseXML(file, oldStageIndex, newStageIndex));
        if (myCurrentGrids.get(newStageIndex) != null) {
            initializeNewStageProps(stage);
            makeGUI(stage);
        }
        else {
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
        int cellsize = 40; //TODO import this from XML
        int stageIndex = myStages.indexOf(stage);
        myGUIs.add(stageIndex, new GUIManager(myCurrentGrids.get(stageIndex),
                this, mySimTypes.get(stageIndex), myShapes.get(stageIndex), stageIndex, cellsize));
        stage.setResizable(false);
        stage.setTitle(TITLE);
        stage.setScene(myGUIs.get(stageIndex).getScene());
        stage.show();
    }

    private Grid parseXML(File dataFile, int oldStageIndex, int newStageIndex) {
        XMLReader testRead = new XMLReader();
        SimulationInfo testSim = testRead.getSimulation(dataFile);
        Grid gridType = null;

        if(testSim.getType() != null) {
            mySimTypes.add(newStageIndex, testSim.getType());
            myShapes.add(newStageIndex, testSim.getShape());

            switch (testSim.getType()) {
                case SEGREGATION:
                    SegregationRuleSet rules = new SegregationRuleSet(testSim.getParameters());
                    gridType = new SegregationGrid(testSim.getHeight(), testSim.getWidth(),
                            testSim.getIntegerConfiguration(), rules);
                    break;
                case GAME_OF_LIFE:
                    GameOfLifeRuleSet rules2 = new GameOfLifeRuleSet();
                    gridType = new GameOfLifeGrid(testSim.getHeight(), testSim.getWidth(),
                            testSim.getIntegerConfiguration(), rules2);
                    break;
                case FIRE:
                    FireRuleSet rules3 = new FireRuleSet(testSim.getParameters());
                    gridType = new FireGrid(testSim.getHeight(), testSim.getWidth(),
                            testSim.getIntegerConfiguration(), rules3);
                    break;
                case PERCOLATION:
                    PercolationRuleSet rules4 = new PercolationRuleSet();
                    gridType = new PercolationGrid(testSim.getHeight(), testSim.getWidth(),
                            testSim.getIntegerConfiguration(), rules4);
                    break;
                case PREDATOR_PREY:
                    PredatorPreyRuleSet rules5 = new PredatorPreyRuleSet(testSim.getParameters());
                    gridType = new PredatorPreyGrid(testSim.getHeight(),testSim.getWidth(),
                            testSim.getIntegerConfiguration(),rules5);
                    break;
            }
        }
        else {
            myGUIs.get(oldStageIndex).errorBox("Load Error", "Invalid XML file");
            return null;
        }
        return gridType;
    }

    private void initializeTimer(int stageIndex) {
        myTimers.add(stageIndex, new AnimationTimer() {
            private long lastUpdate = 0;
            @Override
            public void handle(long currTime) {
                if (currTime - lastUpdate >= mySpeeds.get(stageIndex) * 1000000000) {
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

    void loadNewSim(int stageIndex) {
        File newFile = myFileChooser.showOpenDialog(new Stage());
        if (newFile != null) {
            Stage newStage = new Stage();
            openFile(newFile, newStage, stageIndex);
        }
        else myGUIs.get(stageIndex).errorBox("Load Error", "Invalid file selection");
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
        if (isPaused.get(stageIndex)) startSim(stageIndex);
        else {
            isPaused.set(stageIndex, true);
            myTimers.get(stageIndex).stop();
        }
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