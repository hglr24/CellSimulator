package Simulation;

import java.util.List;

import static Simulation.FireState.*;

public class AntRuleSet implements RuleSet {

    public AntRuleSet(double[] parameters) {
        //probCatch = parameters[0];
    }

    public AntRuleSet() {}

    public void setParameters(double[] parameters) {

    }

    @Override
    public State applyRules(List<Cell> neighbors, Cell cell, Grid grid) {
        AntCell antCell = (AntCell) cell;
        antCell.diffuse();
        antCell.evaluate((AntGrid) grid);

        if(antCell.hasAnts()){
            if(antCell.hasFoodAnts())
                return AntState.ANT_WITH_FOOD;
            else
                return AntState.ANT_NO_FOOD;
        }
    return cell.getCurrentState();

    }




}
