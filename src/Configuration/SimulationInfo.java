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
            "States",
            "SimParameters"
    );

    private String myTitle;
    private String myConfiguration;
    private String myWidth;
    private String myHeight;
    private String myStates;
    private String myParameters;
    private Map<String, String> myValues;

    public SimulationInfo(String title, String configuration, String width, String height, String states, String parameters){
        myTitle = title;
        myConfiguration = configuration;
        myWidth = width;
        myHeight = height;
        myStates = states;
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

    public String getConfiguration(){
        return myConfiguration;
    }

    public String getWidth(){
        return myWidth;
    }

    public String getHeight(){
        return myHeight;
    }

    public String getStates(){
        return myStates;
    }

    public String getParameteres(){
        return myParameters;
    }

    @Override
    public String toString () {
        var result = new StringBuilder();
        result.append(type + " {\n");
        for (var e : myValues.entrySet()) {
            result.append("  ").append(e.getKey()).append("='").append(e.getValue()).append("',\n");
        }
        result.append("}\n");
        return result.toString();
    }

}
