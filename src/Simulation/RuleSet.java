package Simulation;

import java.util.List;

public interface RuleSet{
    State applyRules(List<Cell> neighbors, Cell cell, Grid grid);

    void setParameters(double[] parameters);

    double[] getParameters();
}
