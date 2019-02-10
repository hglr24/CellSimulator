package Configuration;

import Simulation.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import java.util.List;
import java.util.Map;

import static Simulation.Edge.*;
import static Simulation.SimulationType.*;

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

    public SimulationInfo(String title, String simType, String configuration, String width, String height, String shape,
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
        myParameters = checker.checkValidParameters(mySimType, myParameters);
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
    }

    public SimulationInfo(Map<String, String> values) {
        this(values.get(dataFields.get(0)), values.get(dataFields.get(1)), values.get(dataFields.get(2).trim()),
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

    public String getTitle(){
        return myTitle;
    }

    public SimulationType getType(){
        return mySimType;
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

    public Grid getGridType() {
        return myGridType;
    }

    public Neighborhood getNeighborhood(){
        return myNeighborhood;
    }

    public Edge getEdge(){
        return myEdge;
    }

    public int getCellSize(){
        return myCellSize;
    }

    public Paint[] getStateColors(){
        return myColors;
    }

    public boolean getOutline(){
        return myOutline;
    }

    public double[] getParameters(){
        return myParameters;
    }

    public int getWidth(){
        return Integer.parseInt(myWidth.trim());
    }

    public int getHeight(){
        return Integer.parseInt(myHeight.trim());
    }

    public Shape getShape(){
        return myShape;
    }

    private double[] getProbability(){
        return myProbabilities;
    }

    public int[][] getIntegerConfiguration(){
        return myConfiguration;
    }
}
