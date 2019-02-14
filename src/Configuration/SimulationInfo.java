package Configuration;

import Simulation.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import static Simulation.Edge.*;
import static Simulation.SimulationType.*;

/**
 * Create a SimulationInfo object using an XMLReader object that holds the desired XML to check that all fields are valid
 * and set them to default values if they are invalid. The SimulationInfo object will then hold all of the intial values
 * for the simulation.
 */
public class SimulationInfo {
    static final List<String> dataFields = List.of(
            "Title",
            "SimulationType",
            "GridConfiguration",
            "GridWidth",
            "GridHeight",
            "Shape",
            "SimParameters",
            "RandomProbabilities",
            "Neighborhood",
            "Edge",
            "GridSize",
            "OutlineFlag",
            "StateColors"
    );

    private String myTitle;
    private SimulationType mySimType;
    private int[][] myConfiguration;
    private String myWidth;
    private String myHeight;
    private Shape myShape;
    private double[] myParameters;
    private double[] myProbabilities;
    private Neighborhood myNeighborhood;
    private Edge myEdge;
    private int myCellSize;
    private boolean myOutline;
    private Paint[] myColors;
    private String myConfigurationType;
    private String trueRandom = "True Random";
    private Grid myGridType;
    private List<String> myValues;

    private void init(String title, String simType, String configuration, String width, String height, String shape,
                          String parameters, String probabilities, String neighborhood, String edge, String gridSize, String outline, String stateColors) {
        myTitle = title;
        myWidth = width;
        myHeight = height;
        myShape = stringToShape(shape);
        myParameters = parseDoubles(parameters);
        myProbabilities = parseDoubles(probabilities);
        myConfigurationType = configuration;
        setSimType(simType);
        configureSimSpecific(configuration);
        CheckParameters checker = new CheckParameters();
        myParameters = checker.checkValidParameters(mySimType, myParameters, new double[0]);
        myProbabilities = checker.checkValidProbabilities(mySimType, myProbabilities);
        try {
            myNeighborhood = stringToNeighborhood(neighborhood);
        } catch (XMLException e) {
            myNeighborhood = Neighborhood.CARDINAL;
            System.out.println("Default neighborhood used.");
        }
        try {
            myEdge = stringToEdge(edge);
        } catch (XMLException e) {
            myEdge = FINITE;
        }
        try {
            myCellSize = stringToCellSize(gridSize);
        } catch (XMLException e) {
            myCellSize = 15;
        }
        try {
            myOutline = stringToBoolean(outline);
        } catch (XMLException e) {
            myOutline = false;
        }
        try {
            myColors = stringToPaintArray(stateColors);
        } catch (Exception e) {
            myColors = new Paint[]{Color.WHITE, Color.BLACK, Color.RED};
        }
        setGridandRules(); //Sets grid type and ruleset through the SimulationType enum
        String[] arrayValues = new String[]{title, simType, configuration, width, height, shape, parameters,
                probabilities, neighborhood, edge, gridSize, outline, stateColors};
        myValues = new ArrayList<String>();
        myValues.addAll(Arrays.asList(arrayValues));
    }

    /**
     * Pass all of the values read from the xml into the constructor of the SimulationInfo object. The constructor
     * will then check the validity of each of the values, using a CheckParameters object for the probabilities and parameters
     * as those can be changed throughout the progression of the program, and so a special object was created to check them.
     * Each of the values, including title, width, height, shape, parameters, probabilities, configuration type (either
     * string of integers or "Random" or "True Random"), neighborhood, edge type, cell size, outline, and colors of the cells
     * are checked and put into their instance values.
     * @param values Is the list of initial parameters read from the XML file, in the order of title, simulation type, initial
     *               integer configuration or "Random" string, width, height, shape, parameters, probabilities, neighborhood,
     *               edge, grid size, outline, and state colors.
     */
    public SimulationInfo(Map<String, String> values) {
        init(values.get(dataFields.get(0)), values.get(dataFields.get(1)), values.get(dataFields.get(2).trim()),
                values.get(dataFields.get(3)), values.get(dataFields.get(4)), values.get(dataFields.get(5)),
                values.get(dataFields.get(6)), values.get(dataFields.get(7)), values.get(dataFields.get(8)),
                values.get(dataFields.get(9)), values.get(dataFields.get(10)), values.get(dataFields.get(11)), values.get(12));
    }

    private void setGridandRules() {
        RuleSet rules = mySimType.setRules(getParameters());
        myGridType = mySimType.setGrid(getHeight(), getWidth(), getIntegerConfiguration(), rules, getNeighborhood());
    }

    private void setSimType(String type) {
        try {
            mySimType = SimulationType.valueOf(type);
        }
        catch (IllegalArgumentException e) {
            mySimType = GAME_OF_LIFE;
            throw new XMLException("Not a valid simulation type. Game of Life default simulation enabled.");
        }
    }

    private void configureSimSpecific(String configurationString) {
        boolean random = configurationString.trim().equals("Random") || configurationString.trim().equals(trueRandom);
        try {
            int numStates = mySimType.getState().length;
            int stateRange = numStates - 1;
            if (random) {
                myConfiguration = this.getRandom(configurationString.trim(), numStates);
            } else {
                myConfiguration = stringToArray(configurationString.trim(), new int[this.getHeight()][this.getWidth()], numStates);
            }
            if (mySimType.equals(PERCOLATION)) {
                stateRange++;
                percolationInit();
            }
            try {
                inCorrectRange(0, stateRange, this.getIntegerConfiguration());
            }
            catch(XMLException e) {
                System.out.println("Incorrect parameters or probabilities for fire. Default enabled");
                configureSimSpecific(trueRandom);
            }
        }
        catch(XMLException e){
            System.out.print("Not a valid simulation type. Default enabled");
            configureSimSpecific(trueRandom);
        }
    }

    private void percolationInit() {
        int startFill = 0;
        for (int k = 0; k < this.getHeight(); k++) {
            for (int j = 0; j < this.getWidth(); j++) {
                if (startFill == 0 && k == 0) {
                    myConfiguration[k][j] = 2;
                    startFill =1 ;
                }
            }
        }
    }

    private int[][] getRandom(String randomness, int numStates) {
        try {
            int[][] configuration = new int[this.getHeight()][this.getWidth()];
            double probEmpty;
            if (numStates == 2) {
                probEmpty = this.getProbability()[0];
                if (randomness.equals(trueRandom)) {
                    probEmpty = 0.5;
                }
                for (int k = 0; k < this.getHeight(); k++) {
                    for (int j = 0; j < this.getWidth(); j++) {
                        double singleProb = Math.random();
                        if (singleProb < probEmpty) {
                            configuration[k][j] = 0;
                        } else {
                            configuration[k][j] = 1;
                        }
                    }
                }
                return configuration;
            } else if (numStates == 3) {
                probEmpty = this.getProbability()[0];
                double probAgent1 = this.getProbability()[1];
                if (randomness.equals(trueRandom)) {
                    probEmpty = 0.33;
                    probAgent1 = 0.33;
                }
                for (int k = 0; k < this.getHeight(); k++) {
                    for (int j = 0; j < this.getWidth(); j++) {
                        double singleProb = Math.random();
                        if (singleProb < probEmpty) {
                            configuration[k][j] = 0;
                        } else if (singleProb < probEmpty + probAgent1) {
                            configuration[k][j] = 1;
                        } else {
                            configuration[k][j] = 2;
                        }
                    }
                }
                return configuration;
            }
            throw new XMLException("Only two or three states allowed");
        } catch (XMLException e) {
            System.out.println("More than two states entered into random configuration generator");
            return this.getRandom(randomness, 2);
        }
    }

    private Paint[] stringToPaintArray(String stateColors){
        String[] stringColors = stateColors.split(",");
        Paint[] colorPaints = new Paint[stringColors.length];
        for(int k = 0; k < stringColors.length; k++){
            stringColors[k] = stringColors[k].trim();
            colorPaints[k] = Paint.valueOf(stringColors[k]);
        }
        return colorPaints;
    }

    private int[][] stringToArray(String numbers, int[][] configuration, int numStates){
        String[] splitString = myConfigurationType.replaceAll("[\\t\\n\\r]+"," ").replaceAll("\\s","").split("");
        try{
            checkValidSize(splitString);
        }
        catch(XMLException e){
            myConfigurationType = trueRandom;
            return this.getRandom(myConfigurationType, numStates);
        }
        for(int k = 0; k < this.getHeight(); k++){
            for(int j = 0; j < this.getWidth(); j++){
                configuration[k][j] = Integer.parseInt(splitString[(this.getWidth()*k + j)].trim());
            }
        }
        return configuration;
    }

    private void checkValidSize(String[] stringLocations){
        if(this.getHeight() * this.getWidth() != stringLocations.length){
            throw new XMLException("Incorrect height and width for initial input configuration. Random configuration of " +
                    "input height and width chosen.");
        }
    }

    private boolean stringToBoolean(String booleanString){
        if(booleanString.trim().equals("True")){
            return true;
        }
        else if(booleanString.trim().equals("False")){
            return false;
        }
        throw new XMLException("Must state True or False");
    }

    private int stringToCellSize(String sizeString){
        if(Integer.parseInt(sizeString.trim()) > 0 && Integer.parseInt(sizeString.trim()) < 1000){
            return Integer.parseInt(sizeString.trim());
        }
        throw new XMLException("Not a valid grid size");
    }

    private Edge stringToEdge(String edgeString){
        switch(edgeString){
            case "Finite":
                return FINITE;
            case "Infinite":
                return INFINITE;
            case "Toroidal":
                return TOROIDAL;
        }
        throw new XMLException("Not a valid edge type");
    }

    private Neighborhood stringToNeighborhood(String neighborhoodString){
        switch(neighborhoodString.trim()){
            case "Square":
                return Neighborhood.SQUARE;
            case "Triangle":
                return Neighborhood.TRIANGLE;
            case "Hexagon":
                return Neighborhood.HEXAGON;
            case "Cardinal":
                return Neighborhood.CARDINAL;
        }
        throw new XMLException("Not a valid neighborhood.");
    }




    private void inCorrectRange(int low, int high, int[][] configuration){
        for(int k = 0; k < configuration.length; k++){
            for(int j = 0; j < configuration[0].length; j++){
                if(configuration[k][j] > high || configuration[k][j] < low){
                    throw new XMLException("Desired configuration contains illegal values. Random starting configuration" +
                            "enabled.");
                }
            }
        }
    }

    private double[] parseDoubles(String parameters){
        String[] splitParams = parameters.replaceAll("\\s","").split(",");
        double[] paramsOut = new double[splitParams.length];
        try {
            for (int k = 0; k < splitParams.length; k++) {
                paramsOut[k] = Double.parseDouble(splitParams[k]);
            }
            return paramsOut;
        }
        catch(NumberFormatException e){
            return new double[0];
        }
    }

    private Shape stringToShape(String shapeName){
        switch(shapeName){
            case "Triangle":
                return Shape.TRIANGLE;
            case "Square":
                return Shape.SQUARE;
            case "Hexagon":
                return Shape.HEXAGON;
        }
        return Shape.SQUARE;
    }

    /**
     * Used to get the title read in from the XML file
     * @return a string corresponding to the title of the simulation
     */
    public String getTitle(){
        return myTitle;
    }

    /**
     * Getter used to get the type of simulation loaded in from the xml file.
     * @return a SimulationType enumeration corresponding to the type of simulation.
     */
    public SimulationType getType(){
        return mySimType;
    }

    /**
     * Used to return the type of grid used so only a single switch case statement is necessary to initialize a
     * grid subclass instead of putting another switch case in main.
     * @return Grid subclass corresponding to the type of simulation.
     */
    public Grid getGridType() {
        return myGridType;
    }

    /**
     * Getter used to get the neighborhood used to apply rules to each cell.
     * @return Neighborhood enumeration type corresponding to neighbors that should be considered in calculating the
     * state of the current cell.
     */
    public Neighborhood getNeighborhood(){
        return myNeighborhood;
    }

    /**
     * Getter used to get the type of edge (toroidal, infinite, or finite)
     * @return Edge enumeration type corresponding to desired edge
     */
    public Edge getEdge(){
        return myEdge;
    }

    /**
     * Getter used to get the maximum size of a cell in each direction in value of int, regardless of shape
     * @return integer corresponding to the desired size of each cell in the visualization
     */
    public int getCellSize(){
        return myCellSize;
    }

    /**
     * Getter that will return either true or false, true corresponding to the lines between cells being visualized and
     * false corresponding to the lines not being visualized
     * @return boolean corresponding to whether or not the lines between cells should be visualized (true is visualized,
     * false is not visualized)
     */
    public boolean getOutline(){
        return myOutline;
    }

    /**
     * Returns an array of doubles corresponding to the parameters from the XML file, after they have been checked to be
     * valid by CheckParameters above
     * @return double array of valid parameters for the current simulation
     */
    public double[] getParameters(){
        return myParameters;
    }

    /**
     * Getter for the width of the grid in cells
     * @return An integer corresponding to the number of cells in each row of the simulation
     */
    public int getWidth(){
        return Integer.parseInt(myWidth.trim());
    }

    /**
     * Getter for the height of the grid in cells
     * @return An integer corresponding to the number of cells in each column of the simulation
     */
    public int getHeight(){
        return Integer.parseInt(myHeight.trim());
    }

    /**
     * Gets the shape of the cells for the visualization
     * @return Shape enumeration type corresponding to how cells will be visualized
     */
    public Shape getShape(){
        return myShape;
    }

    /**
     * Returns the entered probabilities from the XML file, given that they were valid otherwise defaults were enabled,
     * @return
     */
    private double[] getProbability(){
        return myProbabilities;
    }

    /**
     * Returns the initial configuration in integers, that later maps to the states in each SimulationGrid subclass.
     * Can be taken in from the xml file (String of integers matching stated size)or generated randomly from entered
     * probabilities ("Random in XML file) or truly randomly, with each State having an equal chance ("True Random" in
     * XML file or default).
     * @return 2 Dimensional integer array corresponding to the inital states of each of the cells in the simulation
     */
    public int[][] getIntegerConfiguration(){
        return myConfiguration;
    }

    /**
     * Return the total list of values. This makes it easier to save the current configuration to an xml, as all that is
     * needed is the information in SimulationInfo and the current grid status then.
     * @return A list of strings containing the title, simulation type, initial integer configuration or "Random" string,
     * width, height, shape, parameters, probabilities, neighborhood, edge, grid size, outline, and state colors.
     */
    public List<String> getSimStrings(){
        return myValues;
    }
}
