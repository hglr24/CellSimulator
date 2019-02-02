package Simulation;

import java.util.List;

public class GameOfLifeRuleSet implements RuleSet {
    @Override
    public State applyRules(List<Cell> neighbors, Cell cell){
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
