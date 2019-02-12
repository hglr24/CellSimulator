package Simulation;

import java.util.Map;
import java.util.TreeMap;

public abstract class RuleSet implements RuleSetInterface {

    protected TreeMap<String, Double> parameters = new TreeMap<>();

    public RuleSet(double[] parameters) {
        setParameters(parameters);
    }

    public RuleSet() {
    }

    public void setParameters(double[] parameters) {
        try {
            int i = 0;
            for (Map.Entry<String, Double> entry : this.parameters.entrySet()) {
                entry.setValue(parameters[i]);
                i++;
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Incorrect Parameter Length, No Further Parameter Changes");
            return;
        }
    }

    public Map<String, Double> getParameters() {
        return parameters;
    }
}
