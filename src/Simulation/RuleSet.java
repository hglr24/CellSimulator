package Simulation;

import java.util.Map;
import java.util.TreeMap;

/**
 * Baseline ruleSet.
 */
public abstract class RuleSet implements RuleSetInterface {

    protected TreeMap<String, Double> parameters = new TreeMap<>();

    /**
     * Initialize with parameters
     * @param parameters
     */
    public RuleSet(double[] parameters) {
        setParameters(parameters);
    }

    public RuleSet() {
    }

    /**
     * Change the parameters
     * @param parameters
     */
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

    /**
     * Get the parameters
     * @return
     */

    public Map<String, Double> getParameters() {
        return parameters;
    }
}
