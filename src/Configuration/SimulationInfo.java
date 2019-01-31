package Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimulationInfo {
    public static final String type = "Configuration.SimulationInfo";
    public static final List<String> dataFields = List.of(
            "Title",
            "GridConfiguration",
            "GridWidth",
            "GridHeight",
            "States",
            "SimParameters"
    );

    private String myTitle;
    private String myConfiguration;
    private int myWidth;
    private int myHeight;
    private String myStates;
    private String myParameters;
    private Map<String, String> myValues;

    public SimulationInfo(String title, String configuration, int width, int height, String states, String parameters){
        myTitle = title;
        myConfiguration = configuration;
        myWidth = width;
        myHeight = height;
        myStates = states;
        myParameters = parameters;
        myValues = new HashMap<>();
    }

    public SimulationInfo(Map<String, String> values){
        this(values.get(dataFields.get(0)), values.get(dataFields.get(1)), Integer.parseInt(values.get(dataFields.get(2))),
                Integer.parseInt(values.get(dataFields.get(3))), values.get(dataFields.get(4)), values.get(dataFields.get(5)));
        myValues = values;
    }

    public String getTitle(){
        return myTitle;
    }

    public String getConfiguration(){
        return myConfiguration;
    }

    public int getWidth(){
        return myWidth;
    }

    public int getHeight(){
        return myHeight;
    }

    public String getStates(){
        return myStates;
    }

    public String getParameteres(){
        return myParameters;
    }
}
