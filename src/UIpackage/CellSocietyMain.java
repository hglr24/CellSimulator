package UIpackage;

import Configuration.*;
import Simulation.*;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;

public class CellSocietyMain extends Application {
    private static final String TITLE = "CellSociety";
    private Stage myStage;
    private SimulationType mySimType;
    private String myShape;
    private GUIManager myGUI;
    private Grid myCurrentGrid;
    private AnimationTimer myTimer;
    private static final FileChooser myFileChooser = new FileChooser();
    private double mySpeed;
    private boolean hasStarted = false;
    private boolean isPaused = false;
    private boolean simChanged;

    @Override
    public void start (Stage stage) {
        myStage = stage;
        initializeTimer();
        initializeFileOpener();

        //File dataFile = new File("data\\TestSegregation.xml");
        //File dataFile = new File("data\\TestGameOfLife.xml");
        //File dataFile = new File("data\\TestFire.xml");
        File dataFile = new File("data\\TestPercolation.xml");

        openFile(dataFile);
    }

    private void openFile(File file) {
        myCurrentGrid = parseXML(file);
        if (simChanged) {
            mySpeed = 1;
            makeGUI(myStage);
        }
    }

    private void makeGUI(Stage stage) {
        myGUI = new GUIManager(myCurrentGrid, this, mySimType, myShape);
        stage.setResizable(false);
        stage.setTitle(TITLE);
        stage.setScene(myGUI.getScene());
        stage.show();
    }

    private Grid parseXML(File dataFile) {
        XMLReader testRead = new XMLReader();
        SimulationInfo testSim = testRead.getSimulation(dataFile);
        Grid gridType = null;
        simChanged = false;

        if(testSim.getType() != null) {
            mySimType = testSim.getType();
            myShape = testSim.getShape();
            simChanged = true;

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
                //TODO make cases for each sim type
            }
        }
        else {
            myGUI.errorBox("Load Error", "Invalid XML file");
            return myCurrentGrid;
        }
        return gridType;
    }

    private void initializeTimer() {
        myTimer = new AnimationTimer() {
            private long lastUpdate = 0;
            @Override
            public void handle(long currTime) {
                if (currTime - lastUpdate >= mySpeed * 1000000000) {
                    stepSim();
                    lastUpdate = currTime;
                }
            }
        };
    }

    private void initializeFileOpener() {
        myFileChooser.setTitle("Open Simulation XML File");
        myFileChooser.setInitialDirectory(new File(System.getProperty("user.dir") + "/data"));
        myFileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Simulation XML", "*.xml"));
    }

    void loadNewSim() {
        hasStarted = false;
        myTimer.stop();
        File newFile = myFileChooser.showOpenDialog(new Stage());
        if (newFile != null) {
            openFile(newFile);
        }
        else myGUI.errorBox("Load Error", "Invalid file selection");
    }

    void startSim() {
        hasStarted = true;
        isPaused = false;
        myTimer.start();
    }

    void stepSim() {
        myCurrentGrid.update();
        myGUI.updateGrid(myCurrentGrid);
    }

    void pauseSim() {
        if (isPaused) startSim();
        else {
            isPaused = true;
            myTimer.stop();
        }
    }

    public void changeSim(SimulationType newType) {

    }

    void setSpeed(double newSpeed) {
        mySpeed = 1 / newSpeed;
    }

    double getSpeed() {
        return mySpeed;
    }

    boolean hasStarted() {
        return hasStarted;
    }

    boolean isPaused() {
        return isPaused;
    }

    /**
     * Start of the program.
     */
    public static void main (String[] args) {
        launch(args);
    }
}