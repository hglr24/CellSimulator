package Simulation;

import java.util.List;

import static Simulation.FireState.*;

public class FireRuleSet extends RuleSet {

    private final double DEFAULT_BURN_PROBABILITY = .5;

    @Override
    public void setParameters(double[] parameters) {
        this.parameters.put("burnProbability", DEFAULT_BURN_PROBABILITY);
        super.setParameters(parameters);
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

                if (fireNear && Math.random() < parameters.get("burnProbability")) {
                    return BURNING;
                } else {
                    return TREE;
                }
        }

    }
}
