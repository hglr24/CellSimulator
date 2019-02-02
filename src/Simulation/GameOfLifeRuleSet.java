package Simulation;

import java.util.List;

public class GameOfLifeRuleSet implements RuleSet {
    @Override
    public State applyRules(List<Cell> neighbors, Cell cell){
        return GameOfLifeState.DEAD;
    }

}
