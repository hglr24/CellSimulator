package Simulation;

import java.util.List;

import static Simulation.FireState.*;

public class AntRuleSet implements RuleSet {

    private double probCatch;

    //new idea, simplify the game

    //might need to move constants here

    public AntRuleSet(double[] parameters) {
        probCatch = parameters[0];
    }

    public AntRuleSet() {}

    public void setParameters(double[] parameters) {
        probCatch = parameters[0];
    }

    @Override
    public State applyRules(List<Cell> neighbors, Cell cell, Grid grid) {
        ((AntCell) cell).evaluate((AntGrid) grid);

        if(((AntCell) cell).hasAnts()){
            if(((AntCell) cell).hasFoodAnts())
                return AntState.ANT_WITH_FOOD;
            else
                return AntState.ANT_NO_FOOD;
        }
    return cell.getCurrentState();

    }
}
