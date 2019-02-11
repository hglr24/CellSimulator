package Simulation;

import java.util.List;

import static Simulation.FireState.*;

public class FireRuleSet implements RuleSet {

    private double[] myParams;
    private double probCatch;

    public FireRuleSet(double[] parameters) {
        setParameters(parameters);
    }

    public FireRuleSet() {}

    public void setParameters(double[] parameters) {
        myParams = parameters;
        probCatch = parameters[0];
    }

    public double[] getParameters() {
        return myParams;
    }

    @Override
    public State applyRules(List<Cell> neighbors, Cell cell, Grid grid) {
        switch ((FireState) cell.getCurrentState()) {
            case BURNING:
                return EMPTY;
            case EMPTY:
                return EMPTY;
            default:
                boolean fireNear = false;
                for (Cell c : neighbors) {
                    if (c.getCurrentState() == BURNING) {
                        fireNear = true;
                        break;
                    }
                }

                if (fireNear && Math.random() < probCatch) {
                    return BURNING;
                } else {
                    return TREE;
                }
        }

    }
}
