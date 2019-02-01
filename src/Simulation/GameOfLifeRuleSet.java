package Simulation;

import java.util.List;

public class GameOfLifeRuleSet implements RuleSet {
    public State determineState(List<Cell> neighbors, Location location){
        return GameOfLifeState.DEAD;
    }
}
