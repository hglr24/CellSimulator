package Simulation;

import java.util.List;

/**
 * Rules for Game of Life
 */
public class GameOfLifeRuleSet extends RuleSet {

    /**
     * Apply the Game of Life rules. There is a min and max number of live neighbors that dictates life and death
     * @param neighbors
     * @param cell
     * @param grid
     * @return
     */
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
