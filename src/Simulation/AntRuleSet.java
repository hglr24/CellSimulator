package Simulation;

import java.util.List;

import static Simulation.FireState.*;

public class AntRuleSet implements RuleSet {

    private double probCatch;

    public AntRuleSet(double[] parameters) {
        probCatch = parameters[0];
    }

    @Override
    public State applyRules(List<Cell> neighbors, Cell cell, Grid grid) {
        ((AntCell) cell).evaluate((AntGrid) grid);

    return cell.getCurrentState();

    }
}
