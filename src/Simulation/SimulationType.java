package Simulation;

public enum SimulationType {
    GAME_OF_LIFE, SEGREGATION, PREDATOR_PREY, FIRE, PERCOLATION, RPS;

    @Override
    public String toString() {
        switch(this)
        {
            case GAME_OF_LIFE:
                return "Game of Life";
            case SEGREGATION:
                return "Segregation";
            case PREDATOR_PREY:
                return "Predator/Prey";
            case FIRE:
                return "Fire";
            case PERCOLATION:
                return "Percolation";
            case RPS:
                return "Rock Paper Scissors";
        }
        return "NULL";
    }

    public State[] getState() {
        switch(this)
        {
            case GAME_OF_LIFE:
                return GameOfLifeState.values();
            case SEGREGATION:
                return SegregationState.values();
            case PREDATOR_PREY:
                return PredatorPreyState.values();
            case FIRE:
                return FireState.values();
            case PERCOLATION:
                return PercolationState.values();
            case RPS:
                return RPSState.values();
        }
        return null;
    }
}