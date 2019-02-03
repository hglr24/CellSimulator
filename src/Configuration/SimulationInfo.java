package Configuration;

import Simulation.SimulationType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static Simulation.SimulationType.*;

public class SimulationInfo {
    public static final String type = "Simulation";
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
    private String myShape;
    private String myParameters;
    private Map<String, String> myValues;
    private String wrongParameters = "Incorrect parameters for stated simulation type";

    public SimulationInfo(String title, String simType, String configuration, String width, String height, String shape, String parameters){
        myTitle = title;
        mySimType = stringToType(simType);
        myConfiguration = configuration;
        myWidth = width;
        myHeight = height;
        myShape = shape;
        myParameters = parameters;
        myValues = new HashMap<>();
    }

    public SimulationInfo(Map<String, String> values){
        this(values.get(dataFields.get(0)), values.get(dataFields.get(1)), values.get(dataFields.get(2).trim()),
                values.get(dataFields.get(3)), values.get(dataFields.get(4)), values.get(dataFields.get(5)), values.get(dataFields.get(6)));
        myValues = values;
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
        return null;
    }

    public String getTitle(){
        return myTitle;
    }

    public SimulationType getType(){
        return mySimType;
    }

    public int[][] getIntegerConfiguration(){
        int[][] configuration = new int[this.getHeight()][this.getWidth()];

        if(this.getTitle().trim().equals("Segregation")){
            if(this.getParameters().length != 3){
                throw new XMLException(wrongParameters);
            }
            double probEmpty = this.getParameters()[1];
            double probAgent1 = this.getParameters()[2];
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

        if(this.getTitle().trim().equals("Percolation")){
            int startFill = 0;
            if(this.getParameters().length != 1){
                throw new XMLException(wrongParameters);
            }
            double probEmpty = this.getParameters()[0];
            for(int k = 0; k < this.getHeight(); k++){
                for(int j = 0; j < this.getWidth(); j++){
                    double singleProb = Math.random();
                    if(singleProb < probEmpty){
                        if(startFill == 0 && (j == 0 || k == 0)){
                            configuration[k][j] = 1;
                            startFill++;
                        }
                        configuration[k][j] = 0;
                    }
                    else{
                        configuration[k][j] = 2;
                    }
                }
            }
            return configuration;
        }

        String[] splitString = myConfiguration.replaceAll("[\\t\\n\\r]+"," ").replaceAll("\\s","").split("");
        for(int k = 0; k < this.getHeight(); k++){
            for(int j = 0; j < this.getWidth(); j++){
                configuration[k][j] = Integer.parseInt(splitString[(this.getWidth()*k + j)].trim());
            }
        }
        return configuration;
    }

    public int getWidth(){
        int width = Integer.parseInt(myWidth.trim());
        return width;
    }

    public int getHeight(){
        int height = Integer.parseInt(myHeight.trim());
        return height;
    }

    public String getShape(){
        return myShape;
    }

    public double[] getParameters(){
        String[] splitParams = myParameters.replaceAll("\\s","").split(",");
        double[] paramsOut = new double[splitParams.length];
        for(int k = 0; k < splitParams.length; k++){
            paramsOut[k] = Double.parseDouble(splitParams[k]);
        }
        return paramsOut;
    }

}
