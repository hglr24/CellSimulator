package Simulation;

import java.util.List;

import static Simulation.FireState.*;

public class PredatorPreyRuleSet implements RuleSet {

    private double sharkHealth;
    private double fishReproduce;
    private double sharkReproduce;

    public PredatorPreyRuleSet(double[] parameters) {

        sharkHealth = parameters[0];
        fishReproduce = parameters[1];
        sharkReproduce = parameters[2];
    }

    @Override
    public State applyRules(List<Cell> neighbors, Cell cell, Grid grid) {
        //a lot here
        switch ((PredatorPreyState) cell.getCurrentState()) {
            case FISH:
                return EMPTY;
            case SHARK:
                return EMPTY;
            default:
                return EMPTY;

        }

    }
}
