package Simulation;

import java.util.ArrayList;
import java.util.List;

/**
 * Sets the way that the SugarScape simulation updates
 */
public class SugarScapeRuleSet extends RuleSet {
    private final double DEFAULT_METABOLISM = 1;
    private final double DEFAULT_GROW_RATE = 1;

    /**
     * Set the parameters that affect the way SugarScape simulation updates
     * @param parameters is a double array with the metabolism as the first entry and the grow rate as the second
     */
    @Override
    public void setParameters(double[] parameters) {
        this.parameters.put("metabolism", DEFAULT_METABOLISM);
        this.parameters.put("growRate", DEFAULT_GROW_RATE);
        super.setParameters(parameters);
    }

    /**
     * Decides the next state of the current cell and if the current cell has/had an agent
     * @param neighbors is the list of cells that affect the state of the current cell
     * @param cell is the current cell
     * @param grid is the grid that the cell is in
     * @return is the next state of the current cell
     */
    public State applyRules(List<Cell> neighbors, Cell cell, Grid grid){
        SugarScapeCell sugarCell = (SugarScapeCell) cell;
        if(sugarCell.getRegenDays() == 0){
            if(sugarCell.getSugar() < sugarCell.getMaxSugar()){
                sugarCell.setSugar(sugarCell.getSugar() + 1);
            }
            sugarCell.setRegenDays(sugarCell.getMaxRegenDays());
        }
        else{
            sugarCell.setRegenDays(sugarCell.getRegenDays() - 1);
        }

        if(sugarCell.getAgent()){
            List<SugarScapeCell> sugarNeighbors = new ArrayList<SugarScapeCell>();
            for(Cell c:neighbors){
                sugarNeighbors.add((SugarScapeCell) c);
            }
            moveAgent(sugarCell, sugarNeighbors);
        }
        if(sugarCell.getSugar() == sugarCell.getMaxSugar()){
            return SugarScapeState.SUGAR2;
        }
        else if(sugarCell.getSugar() == 1){
            return SugarScapeState.SUGAR1;
        }
        return SugarScapeState.SUGAR0;
    }

    /**
     * If the cell has an agent, check which of it's neighbors has the most sugar and have the agent go there
     * @param cell is the current cell that has the agent
     * @param neighbors is the list of neighbors that is iterated through to check where the agent should move
     */
    private void moveAgent(SugarScapeCell cell, List<SugarScapeCell> neighbors){
        int maxSugar = 0;
        int maxSugarIndex = 0;
        for(int k = 0; k < neighbors.size(); k++){
            if(neighbors.get(k).getSugar() > maxSugar && !neighbors.get(k).getAgent()){
                maxSugar = neighbors.get(k).getSugar();
                maxSugarIndex = k;
            }
        }
        neighbors.get(maxSugarIndex).setAgent(true);
        cell.setAgent(false);
    }


}
