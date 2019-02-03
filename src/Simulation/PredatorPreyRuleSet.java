package Simulation;

import java.util.List;

import static Simulation.PredatorPreyState.*;

public class PredatorPreyRuleSet implements RuleSet {

    private int sharkHealth;
    private int fishReproduce;
    private int sharkReproduce;

    public PredatorPreyRuleSet(double[] parameters) {

        sharkHealth = (int) parameters[0];
        fishReproduce = (int) parameters[1];
        sharkReproduce = (int) parameters[2];
    }

    @Override
    public State applyRules(List<Cell> neighbors, Cell cell, Grid grid) {
        //a lot here
        switch ((PredatorPreyState) cell.getCurrentState()) {
            case FISH:
                return Fish(neighbors,(PredatorPreyCell) cell,grid);
            case SHARK:
                return EMPTY;
            default:
                return EMPTY;

        }

    }

    private PredatorPreyState Fish(List<Cell> neighbors, PredatorPreyCell cell, Grid grid){
        cell.setFishReproduceDays(cell.getFishReproduceDays()-1);

        //gather options
        for(Cell c: neighbors){
            if(c.nextState == EMPTY){

            }
        }
        //choose random option

        //move

        if(cell.getFishReproduceDays()==0){
            //"also reproduce"
            cell.setFishReproduceDays(fishReproduce);

        }
        return EMPTY;
    }
}
