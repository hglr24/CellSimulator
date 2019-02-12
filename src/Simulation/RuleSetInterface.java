package Simulation;

import java.util.List;
import java.util.Map;

public interface RuleSetInterface {
    State applyRules(List<Cell> neighbors, Cell cell, Grid grid);

    void setParameters(double[] parameters);

    Map<String, Double> getParameters();
}
