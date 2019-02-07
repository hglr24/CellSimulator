package Simulation;

import java.util.List;

import static Simulation.PercolationState.OPEN;
import static Simulation.PercolationState.PERCOLATED;

public class PercolationRuleSet implements RuleSet{
    private boolean hasPercolated;

    public PercolationRuleSet(){
        hasPercolated = false;
    }

    @Override
    public State applyRules(List<Cell> neighbors, Cell baseCell, Grid grid){
        PercolationCell cell = (PercolationCell) baseCell;
        if(cell.currentState == OPEN){
            if(!hasPercolated){
                for(Cell c:neighbors){
                    if(c.getCurrentState() == PERCOLATED){
                        if(cell.getYPosition() == grid.getHeight()-1){
                            System.out.println(cell.getYPosition());
                            hasPercolated = true;
                        }
                        return PERCOLATED;
                    }
                }
            }
        }
        return cell.getCurrentState();
    }
}
