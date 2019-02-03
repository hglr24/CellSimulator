package Simulation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static Simulation.PredatorPreyState.*;

public class PredatorPreyRuleSet implements RuleSet {

    private int sharkHealth;
    private int fishReproduce;
    private int sharkReproduce;

    public PredatorPreyRuleSet(double[] parameters) {

        sharkHealth = (int) parameters[2];
        fishReproduce = (int) parameters[0];
        sharkReproduce = (int) parameters[1];
    }

    @Override
    public State applyRules(List<Cell> neighbors, Cell cell, Grid grid) {
        //a lot here
        switch ((PredatorPreyState) cell.getCurrentState()) {
            case FISH:
                return Fish(neighbors, (PredatorPreyCell) cell);
            case SHARK:
                return Shark(neighbors, (PredatorPreyCell) cell);
            default:
                return EMPTY;

        }

    }

    private PredatorPreyState Shark(List<Cell> neighbors, PredatorPreyCell cell) {
        ArrayList<Cell> open = new ArrayList<>();
        ArrayList<Cell> fish = new ArrayList<>();

        cell.setSharkEnergyDays(cell.getSharkEnergyDays() - 1);

        if(cell.getSharkReproduceDays()>=1)
            cell.setSharkReproduceDays(cell.getSharkReproduceDays()-1);

        if(cell.getSharkEnergyDays()<=0)
            return EMPTY;

        for (Cell c : neighbors) {
            if (c.currentState == EMPTY && c.nextState == EMPTY)
                open.add(c);

            if (c.currentState == FISH && c.nextState != SHARK)
                fish.add(c);
        }
            if(!fish.isEmpty()){

                PredatorPreyCell mover = (PredatorPreyCell) fish.get(new Random().nextInt(fish.size()));
                mover.setSharkReproduceDays(cell.getSharkReproduceDays());
                mover.setSharkEnergyDays(sharkHealth);
                mover.setNextState(SHARK);

                if(cell.getSharkReproduceDays()==0){
                    mover.setSharkReproduceDays(sharkReproduce);
                    cell.setSharkReproduceDays(sharkReproduce);
                    cell.setSharkEnergyDays(sharkHealth);
                    return SHARK;
                } else{
                    return  EMPTY;
                }
            } else if(!open.isEmpty()) {
                PredatorPreyCell mover = (PredatorPreyCell) open.get(new Random().nextInt(open.size()));
                mover.setSharkReproduceDays(cell.getSharkReproduceDays());
                mover.setSharkEnergyDays(cell.getSharkEnergyDays());
                mover.setNextState(SHARK);
                return EMPTY;
            }
                return SHARK;
    }

    private PredatorPreyState Fish(List<Cell> neighbors, PredatorPreyCell cell) {
        if(cell.getFishReproduceDays()>=1)
            cell.setFishReproduceDays(cell.getFishReproduceDays() - 1);

        ArrayList<Cell> open = new ArrayList<>();
        //gather options
        for (Cell c : neighbors) {
            if (c.currentState == EMPTY && c.nextState == EMPTY) {
                open.add(c);
            }
        }
        if (!open.isEmpty()) {
            PredatorPreyCell mover = (PredatorPreyCell) open.get(new Random().nextInt(open.size()));
            mover.setFishReproduceDays(cell.getFishReproduceDays());
            mover.setNextState(FISH);

            if (cell.getFishReproduceDays() == 0) {
                //"also reproduce"
                mover.setFishReproduceDays(fishReproduce);
                cell.setFishReproduceDays(fishReproduce);
                return FISH;
            } else {
                return EMPTY;
            }

        } else {
            return FISH;
        }
    }

    public int getSharkHealth() {
        return sharkHealth;
    }

    public int getFishReproduce() {
        return fishReproduce;
    }

    public int getSharkReproduce() {
        return sharkReproduce;
    }
}
