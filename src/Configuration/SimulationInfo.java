package Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimulationInfo {
    public static final String type = "Simulation";
    public static final List<String> dataFields = List.of(
            "Title",
            "GridConfiguration",
            "GridWidth",
            "GridHeight",
            "Shape",
            "SimParameters"
    );

    private String myTitle;
    private String myConfiguration;
    private String myWidth;
    private String myHeight;
    private String myShape;
    private String myParameters;
    private Map<String, String> myValues;

    public SimulationInfo(String title, String configuration, String width, String height, String shape, String parameters){
        myTitle = title;
        myConfiguration = configuration;
        myWidth = width;
        myHeight = height;
        myShape = shape;
        myParameters = parameters;
        myValues = new HashMap<>();
    }

    public SimulationInfo(Map<String, String> values){
        this(values.get(dataFields.get(0)), values.get(dataFields.get(1)), values.get(dataFields.get(2).trim()),
                values.get(dataFields.get(3)), values.get(dataFields.get(4)), values.get(dataFields.get(5)));
        myValues = values;
    }

    public String getTitle(){
        return myTitle;
    }

    public int[][] getConfiguration(){
        int[][] configuration = new int[this.getHeight()][this.getWidth()];
        String[] splitString = myConfiguration.replaceAll("[\\t\\n\\r]+"," ").replaceAll("\\s","").split("");
        for(int k = 0; k < this.getWidth(); k++){
            for(int j = 0; j < this.getHeight(); j++){
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

    public String getParameteres(){
        return myParameters;
    }
}
