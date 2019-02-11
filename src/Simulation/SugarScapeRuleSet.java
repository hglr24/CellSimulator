package Simulation;

import java.util.ArrayList;
import java.util.List;

public class SugarScapeRuleSet implements RuleSet {
    private int myMetabolism;
    private int myGrowRate;

    public SugarScapeRuleSet(double[] parameters){
        myMetabolism = (int) parameters[0];
        myGrowRate = (int) parameters[1];
    }

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

    public void setParameters(double[] parameters){
        myMetabolism = (int) parameters[0];
        myGrowRate = (int) parameters[1];
    }
}
