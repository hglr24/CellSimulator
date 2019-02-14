package Simulation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static Simulation.PredatorPreyState.*;

/**
 * Rules for Predator Prey
 */
public class PredatorPreyRuleSet extends RuleSet {
    private final double DEFAULT_FISH_REPRODUCTION_TIME = .5;
    private final double DEFAULT_SHARK_REPRODUCTION_TIME = .5;
    private final double DEFAULT_SHARK_ENERGY = .5;

    /**
     * Set parameters.
     * @param parameters
     */
    @Override
    public void setParameters(double[] parameters) {
        this.parameters.put("fishReproductionTime", DEFAULT_FISH_REPRODUCTION_TIME);
        this.parameters.put("sharkEnergy", DEFAULT_SHARK_ENERGY);
        this.parameters.put("sharkReproductionTime", DEFAULT_SHARK_REPRODUCTION_TIME);
        super.setParameters(parameters);
    }

    /**
     * Specific application of the rules
     * @param neighbors
     * @param cell
     * @param grid
     * @return
     */
    @Override
    public State applyRules(List<Cell> neighbors, Cell cell, Grid grid) {
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

        if (cell.getSharkReproduceDays() >= 1)
            cell.setSharkReproduceDays(cell.getSharkReproduceDays() - 1);

        if (cell.getSharkEnergyDays() <= 0)
            return EMPTY;

        for (Cell c : neighbors) {
            if (c.nextState == EMPTY)
                open.add(c);

            if (c.currentState == FISH && c.nextState != SHARK)
                fish.add(c);
        }
        if (!fish.isEmpty()) {

            PredatorPreyCell mover = (PredatorPreyCell) fish.get(new Random().nextInt(fish.size()));
            mover.setSharkReproduceDays(cell.getSharkReproduceDays());
            mover.setSharkEnergyDays(parameters.get("sharkEnergy"));
            mover.setNextState(SHARK);

            if (cell.getSharkReproduceDays() == 0) {
                mover.setSharkReproduceDays(parameters.get("sharkReproductionTime"));
                cell.setSharkReproduceDays(parameters.get("sharkReproductionTime"));
                cell.setSharkEnergyDays(parameters.get("sharkEnergy"));
                return SHARK;
            } else {
                return EMPTY;
            }
        } else if (!open.isEmpty()) {
            PredatorPreyCell mover = (PredatorPreyCell) open.get(new Random().nextInt(open.size()));
            mover.setSharkReproduceDays(cell.getSharkReproduceDays());
            mover.setSharkEnergyDays(cell.getSharkEnergyDays());
            mover.setNextState(SHARK);
            return EMPTY;
        }
        return SHARK;
    }

    private PredatorPreyState Fish(List<Cell> neighbors, PredatorPreyCell cell) {
        if (cell.getFishReproduceDays() >= 1)
            cell.setFishReproduceDays(cell.getFishReproduceDays() - 1);

        ArrayList<Cell> open = new ArrayList<>();
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
                mover.setFishReproduceDays(parameters.get("fishReproductionTime"));
                cell.setFishReproduceDays(parameters.get("fishReproductionTime"));
                return FISH;
            } else {
                return EMPTY;
            }

        } else {
            return FISH;
        }
    }

}
