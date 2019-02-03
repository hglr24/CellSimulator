package UIpackage;

import Configuration.SimulationInfo;
import Configuration.XMLReader;
import Simulation.*;
import javafx.animation.AnimationTimer;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.stage.Stage;
import java.io.File;

/**
 * This will be implemented into SimulationCoordinator later, bare-bones implementation here for testing
 */
public class CellSocietyMain extends Application {
    private static final String TITLE = "CellSociety";
    private SimulationType mySimType;
    private String myShape;
    private GUIManager myGUI;
    private Grid myCurrentGrid;
    private Timeline myTimeline;
    private AnimationTimer myTimer;
    private double mySpeed;
    private boolean hasStarted = false;
    private boolean isPaused = false;

    @Override
    public void start (Stage stage) {
        mySpeed = 1;
        initializeTimeline();
        myCurrentGrid = parseXML();

        myGUI = new GUIManager(myCurrentGrid, this, mySimType, myShape);
        stage.setResizable(false);
        stage.setTitle(TITLE);
        stage.setScene(myGUI.getScene());
        stage.show();
    }

    private void initializeTimeline() {
        myTimeline = new Timeline();
        myTimeline.setCycleCount(Timeline.INDEFINITE);
    }

    private Grid parseXML() {
        XMLReader testRead = new XMLReader();
//       File dataFile = new File("data\\TestSegregation.xml");
       // File dataFile = new File("data\\TestGameOfLife.xml");
        //File dataFile = new File("data\\TestFire.xml");
        File dataFile = new File("data\\TestPercolation.xml");
        SimulationInfo testSim = testRead.getSimulation(dataFile);
        Grid gridType = null;
        myShape = testSim.getShape();
        mySimType = testSim.getType();

        switch(testSim.getType()){
            case SEGREGATION:
                SegregationRuleSet rules = new SegregationRuleSet(testSim.getParameters());
                gridType = new SegregationGrid(testSim.getHeight(),testSim.getWidth(),
                        testSim.getIntegerConfiguration(),rules);
                break;
            case GAME_OF_LIFE:
                GameOfLifeRuleSet rules2 = new GameOfLifeRuleSet();
                gridType = new GameOfLifeGrid(testSim.getHeight(),testSim.getWidth(),
                        testSim.getIntegerConfiguration(),rules2);
                break;
            case FIRE:
                FireRuleSet rules3 = new FireRuleSet(testSim.getParameters());
                gridType = new FireGrid(testSim.getHeight(),testSim.getWidth(),
                        testSim.getIntegerConfiguration(),rules3);
                break;
            case PERCOLATION:
                PercolationRuleSet rules4 = new PercolationRuleSet();
                gridType = new PercolationGrid(testSim.getHeight(),testSim.getWidth(),
                        testSim.getIntegerConfiguration(),rules4);
                break;
                //TODO make cases for each sim type
        }
        return gridType;
    }

    public void startSim() {
        hasStarted = true;
        isPaused = false;

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
        myTimer.start();
    }

    public void stepSim() {
        myCurrentGrid.update();
        myGUI.updateGrid(myCurrentGrid);
    }

    public void pauseSim() {
        if (isPaused) startSim();
        else {
            isPaused = true;
            myTimer.stop();
        }
    }

    public void stopSim() {
        hasStarted = false;
        myTimer.stop();
    }

    public void changeSim(SimulationType newType) {

    }

    public boolean hasStarted() {
        return hasStarted;
    }

    /**
     * Start of the program.
     */
    public static void main (String[] args) {
        launch(args);
    }
}
