package Configuration;

import Simulation.SimulationType;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static Simulation.SimulationType.*;

public class SimulationInfo {
    public static final List<String> dataFields = List.of(
            "Title",
            "SimulationType",
            "GridConfiguration",
            "GridWidth",
            "GridHeight",
            "Shape",
            "SimParameters"
    );

    private String myTitle;
    private SimulationType mySimType;
    private String myConfiguration;
    private String myWidth;
    private String myHeight;
    private Shape myShape;
    private String myParameters;
    private Map<String, String> myValues;

    public SimulationInfo(String title, String simType, String configuration, String width, String height, String shape, String parameters){
        myTitle = title;
        try {
            mySimType = stringToType(simType);
        }
        catch(XMLException e){
            mySimType = GAME_OF_LIFE;
        }
        myConfiguration = configuration;
        myWidth = width;
        myHeight = height;
        myShape = stringToShape(shape);
        myParameters = parameters;
        myValues = new HashMap<>();
        this.checkConfiguration();
    }

    public SimulationInfo(Map<String, String> values){
        this(values.get(dataFields.get(0)), values.get(dataFields.get(1)), values.get(dataFields.get(2).trim()),
                values.get(dataFields.get(3)), values.get(dataFields.get(4)), values.get(dataFields.get(5)), values.get(dataFields.get(6)));
        myValues = values;
    }

    private void checkConfiguration(){
        switch(this.getType()){
            case FIRE:
                try {
                    inCorrectRange(0, 2, this.getIntegerConfiguration());
                }
                catch(XMLException e) {
                    myConfiguration = "True Random";
                }
                break;
            case GAME_OF_LIFE:
                try {
                    inCorrectRange(0, 1, this.getIntegerConfiguration());
                }
                catch(XMLException e) {
                    myConfiguration = "True Random";
                }
                break;
            case PERCOLATION:
                try {
                    inCorrectRange(0, 2, this.getIntegerConfiguration());
                }
                catch(XMLException e) {
                    myConfiguration = "True Random";
                }
                break;
            case PREDATOR_PREY:
                try {
                    inCorrectRange(0, 2, this.getIntegerConfiguration());
                }
                catch(XMLException e) {
                    myConfiguration = "True Random";
                }
                break;
            case SEGREGATION:
                try {
                    inCorrectRange(0, 2, this.getIntegerConfiguration());
                }
                catch(XMLException e) {
                    myConfiguration = "True Random";
                }
                break;
        }
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

    private SimulationType stringToType(String simulationName){
        switch(simulationName)
        {
            case "GAME_OF_LIFE":
                return GAME_OF_LIFE;
            case "SEGREGATION":
                return SEGREGATION;
            case "PREDATOR_PREY":
                return PREDATOR_PREY;
            case "FIRE":
                return FIRE;
            case "PERCOLATION":
                return PERCOLATION;
        }
        throw new XMLException("Not a valid simulation type. Game of Life default simulation enabled.");
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

    public int[][] getIntegerConfiguration(){
        int[][] configuration = new int[this.getHeight()][this.getWidth()];
        if(myConfiguration.trim().equals("Random") || myConfiguration.trim().equals("True Random")) {
            switch (this.getType()) {
                case FIRE:
                    configuration = this.getRandomThreeStates(myConfiguration.trim(), this.getType());
                    return configuration;

                case GAME_OF_LIFE:
                    configuration = this.getRandomGOL(myConfiguration.trim(), this.getType());
                    return configuration;

                case PREDATOR_PREY:
                    configuration = this.getRandomThreeStates(myConfiguration.trim(), this.getType());
                    return configuration;
                case PERCOLATION:
                    configuration = getRandomPercolation(myConfiguration.trim(), this.getType());
                    return configuration;
                case SEGREGATION:
                    configuration = getRandomThreeStates(myConfiguration.trim(), this.getType());
                    return configuration;
            }
        }

        String[] splitString = myConfiguration.replaceAll("[\\t\\n\\r]+"," ").replaceAll("\\s","").split("");
        try{
            checkValidSize(splitString);
        }
        catch(XMLException e){
            myConfiguration = "True Random";
            return this.getIntegerConfiguration();
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

    public int[][] getRandomPercolation(String randomness, SimulationType simtype){
        double probEmpty = this.getParameters()[0];
        if(simtype == GAME_OF_LIFE){
            probEmpty = this.getParameters()[1];
        }
        if(randomness.equals("True Random")){
            probEmpty = 0.5;
        }
        int[][] configuration = new int[this.getHeight()][this.getWidth()];
        int startFill = 0;
        for(int k = 0; k < this.getHeight(); k++){
            for(int j = 0; j < this.getWidth(); j++){
                double singleProb = Math.random();
                if(singleProb < probEmpty){
                    if(startFill == 0 && k == 0){
                        configuration[k][j] = 1;
                        startFill = 1;
                    }
                    else {
                        configuration[k][j] = 0;
                    }
                }
                else{
                    configuration[k][j] = 2;
                }
            }
        }
        return configuration;
    }

    public int[][] getRandomGOL(String randomness, SimulationType simtype){
        double probEmpty = this.getParameters()[1];
        if(randomness.equals("True Random")){
            probEmpty = 0.5;
        }
        int[][] configuration = new int[this.getHeight()][this.getWidth()];
        for(int k = 0; k < this.getHeight(); k++){
            for(int j = 0; j < this.getWidth(); j++){
                double singleProb = Math.random();
                if(singleProb < probEmpty){
                    configuration[k][j] = 0;
                }
                else{
                    configuration[k][j] = 1;
                }
            }
        }
        return configuration;

    }

    public int[][] getRandomThreeStates(String randomness, SimulationType simtype){
        //Defaults to segregation
        int[][] configuration = new int[this.getHeight()][this.getWidth()];
        double probEmpty = this.getParameters()[0];
        double probAgent1 = this.getParameters()[1];
        if(simtype == FIRE){
            probEmpty = this.getParameters()[1];
            probAgent1 = this.getParameters()[2];
        }
        if(simtype == PREDATOR_PREY){
            probEmpty = this.getParameters()[3];
            probAgent1 = this.getParameters()[4];
        }
        if(randomness.equals("True Random")){
            probEmpty = 0.33;
            probAgent1 = 0.33;
        }
        for(int k = 0; k < this.getHeight(); k++){
            for(int j = 0; j < this.getWidth(); j++){
                double singleProb = Math.random();
                if(singleProb < probEmpty){
                    configuration[k][j] = 0;
                }
                else if(singleProb < probEmpty + probAgent1){
                    configuration[k][j] = 1;
                }
                else{
                    configuration[k][j] = 2;
                }
            }
        }
        return configuration;
    }

    public double[] getParameters(){
        String[] splitParams = myParameters.replaceAll("\\s","").split(",");
        double[] paramsOut = new double[splitParams.length];
        for(int k = 0; k < splitParams.length; k++){
            paramsOut[k] = Double.parseDouble(splitParams[k]);
        }
        if(paramsOut.length == 0){
            double[] emptyOut = new double[0];
            return emptyOut;
        }
        return paramsOut;
    }

    public int getWidth(){
        int width = Integer.parseInt(myWidth.trim());
        return width;
    }

    public int getHeight(){
        int height = Integer.parseInt(myHeight.trim());
        return height;
    }

    public Shape getShape(){
        return myShape;
    }
}
