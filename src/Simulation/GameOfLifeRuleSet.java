package Simulation;

import java.util.List;

public class GameOfLifeRuleSet extends RuleSet {

    @Override
    public void setParameters(double[] parameters) {
        super.setParameters(parameters);
    }

    @Override
    public State applyRules(List<Cell> neighbors, Cell cell, Grid grid){
        int live = 0;
        for(Cell c: neighbors){
            if(c.getCurrentState() == GameOfLifeState.LIVE)
                live++;
        }

        if(live<2)
            return GameOfLifeState.DEAD;
        if((live == 2 || live == 3) && cell.getCurrentState() == GameOfLifeState.LIVE)
            return GameOfLifeState.LIVE;
        if(live>3)
            return GameOfLifeState.DEAD;
        if(live == 3)
            return GameOfLifeState.LIVE;

        return GameOfLifeState.DEAD;
    }

}
