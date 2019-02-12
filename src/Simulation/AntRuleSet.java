package Simulation;


import java.util.List;

public class AntRuleSet extends RuleSet {

    @Override
    public void setParameters(double[] parameters) {
        super.setParameters(parameters);
    }

    @Override
    public State applyRules(List<Cell> neighbors, Cell cell, Grid grid) {
        AntCell antCell = (AntCell) cell;

        antCell.evaluate((AntGrid) grid);

        if (cell.getCurrentState() == AntState.FOOD)
            return cell.getCurrentState();

        if (cell.getCurrentState() == AntState.HOME)
            return cell.getCurrentState();

        antCell.diffuse();

        if (antCell.hasAnts()) {
            if (antCell.hasFoodAnts())
                return AntState.ANT_WITH_FOOD;
            else
                return AntState.ANT_NO_FOOD;
        } else {
            return AntState.EMPTY;
        }


    }


}
