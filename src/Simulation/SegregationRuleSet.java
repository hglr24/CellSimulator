package Simulation;

import java.util.List;

import static Simulation.SegregationState.EMPTY;

public class SegregationRuleSet implements RuleSet{
    private double mySatPercent;

    public SegregationRuleSet(double[] parameters){
        mySatPercent = parameters[0];
    }

    public State applyRules(List<Cell> neighbors, Cell cell, Grid grid){
        double sameNeighbors = 0;
        for(Cell c:neighbors){
            if(c.getCurrentState() == cell.getCurrentState()){
                sameNeighbors++;
            }
        }
        double samePercent = sameNeighbors/neighbors.size();
        if(samePercent < mySatPercent ){
            moveToEmpty((SegregationCell) cell, grid);
            return EMPTY;
        }
        return cell.getCurrentState();
    }

    public void moveToEmpty(SegregationCell cell,Grid grid){
        for(int k = 0; k < grid.getHeight(); k++){
            for(int j = 0; j < grid.getWidth(); j++){
                SegregationCell checkCell = (SegregationCell) grid.getCell(new SquareLocation(k, j));
                if(checkCell.getCurrentState() == EMPTY && checkCell.getNextState() == EMPTY){
                    checkCell.setNextState((SegregationState) cell.getCurrentState());
                    return;
                }
            }
        }
    }
}