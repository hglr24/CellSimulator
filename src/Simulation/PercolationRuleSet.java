package Simulation;

import java.util.List;

import static Simulation.PercolationState.OPEN;
import static Simulation.PercolationState.PERCOLATED;

public class PercolationRuleSet implements RuleSet{

    public PercolationRuleSet(){
    }

    @Override
    public State applyRules(List<Cell> neighbors, Cell cell, Grid grid){
        if(cell.currentState == OPEN){
            for(Cell c:neighbors){
                if(c.getCurrentState() == PERCOLATED){
                    return PERCOLATED;
                }
            }
        }
        return cell.getCurrentState();
    }
}
