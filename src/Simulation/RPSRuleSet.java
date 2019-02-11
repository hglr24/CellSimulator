package Simulation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import static Simulation.RPSState.*;


public class RPSRuleSet implements RuleSet {

    private int threshold;
    private double[] myParams;

    public RPSRuleSet(double[] parameters) {
        setParameters(parameters);
    }

    public RPSRuleSet() {}

    public void setParameters(double[] parameters) {
        myParams = parameters;
        threshold = (int) parameters[0];
    }

    public double[] getParameters() {
        return myParams;
    }

    @Override
    public State applyRules(List<Cell> neighbors, Cell cell, Grid grid){
        int tempThreshold = threshold + ThreadLocalRandom.current().nextInt(0, 3);

        Map<State,Integer> cellCounts = new HashMap<>();
        for (State t: RPSState.values()) {
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


    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }
}
