package Simulation;

import java.util.List;

public class RuleKeeper {
    private SimulationType activeSim;

    private GameOfLifeRuleSet gameOfLifeRuleSet;


    public RuleKeeper(){
        gameOfLifeRuleSet = new GameOfLifeRuleSet();
    }

    public State determineState(List<Cell> neighbors, Location location){

        switch(activeSim){
            case GAME_OF_LIFE:
                return gameOfLifeRuleSet.determineState(neighbors, location);
            case FIRE:

            case PERCOLATION:

            case PREDATOR_PREY:

            case SEGREGATION:

            default:
                System.out.println("ERROR, not a simulation");
                return null;
        }

    }

    public void changeSimulation(SimulationType simulationType){
        this.activeSim = simulationType;
    }
}
