package Simulation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static Simulation.SegregationState.EMPTY;

public class SegregationRuleSet extends RuleSet{
    private final double DEFAULT_SATISFACTION_PERCENTAGE = .5;

    @Override
    public void setParameters(double[] parameters) {
        this.parameters.put("satisfactionPercentage", DEFAULT_SATISFACTION_PERCENTAGE);
        super.setParameters(parameters);
    }

    public State applyRules(List<Cell> neighbors, Cell cell, Grid grid){
        double sameNeighbors = 0;
        for(Cell c:neighbors){
            if(c.getCurrentState() == cell.getCurrentState()){
                sameNeighbors++;
            }
        }
        double samePercent = sameNeighbors/neighbors.size();
        if(samePercent < parameters.get("satisfactionPercentage") ){
            moveToEmpty((SegregationCell) cell, grid);
            return EMPTY;
        }
        return cell.getCurrentState();
    }

    public void moveToEmpty(SegregationCell cell,Grid grid){
        ArrayList<SegregationCell> openings = new ArrayList<>();
        for(int k = 0; k < grid.getHeight(); k++){
            for(int j = 0; j < grid.getWidth(); j++){
                SegregationCell checkCell = (SegregationCell) grid.getCell(new SquareLocation(k, j));
                if(checkCell.getCurrentState() == EMPTY && checkCell.getNextState() == EMPTY){
                    openings.add(checkCell);
                }
            }
        }
        if(openings.isEmpty())
            return;
        Collections.shuffle(openings);
        openings.get(0).setNextState((SegregationState) cell.getCurrentState());
    }
}
