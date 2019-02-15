package Simulation;

import java.util.List;
import java.util.Map;

/**
 * Basic contract for a RuleSet, which covers how cells should update.
 */
public interface RuleSetInterface {
    /**
     * Every cell will ask the RuleSet to apply rules to determine the next state of the cell, and any other changes to the cell. This might also
     * mean changing other cells. This should be entirely decidable based on the parameters, and established rules.
     * @param neighbors
     * @param cell
     * @param grid
     * @return
     */
    State applyRules(List<Cell> neighbors, Cell cell, Grid grid);

    /**
     * Set the parameters of the specific simulation
     * @param parameters
     */
    void setParameters(double[] parameters);

    /**
     * Return a map of parameter names and values. This is used by the front-end to update parameters.
     * @return
     */
    Map<String, Double> getParameters();
}
