package Simulation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import static Simulation.RPSState.*;


public class RPSRuleSet extends RuleSet {
    private final double DEFAULT_THRESHOLD = .5;

    @Override
    public void setParameters(double[] parameters) {
        this.parameters.put("winThreshold", DEFAULT_THRESHOLD);
        super.setParameters(parameters);
    }

    @Override
    public State applyRules(List<Cell> neighbors, Cell cell, Grid grid){
        double tempThreshold = this.parameters.get("winThreshold") + ThreadLocalRandom.current().nextInt(0, 3);

        Map<State,Integer> cellCounts = new HashMap<>();
        for (State t: values()) {
            cellCounts.put(t,0);
        }
        for(Cell c: neighbors){
            cellCounts.put(c.getCurrentState(),cellCounts.get(c.getCurrentState())+1);
        }

        switch((RPSState) cell.getCurrentState()){
            case ROCK:
                if(cellCounts.get(PAPER)> tempThreshold){
                    return PAPER;
                }
                break;
            case PAPER:
                if(cellCounts.get(SCISSORS)>tempThreshold){
                    return SCISSORS;
                }
                break;
            case SCISSORS:
                if(cellCounts.get(ROCK)>tempThreshold){
                    return ROCK;
                }
                break;
        }
        return cell.getCurrentState();
    }

}
