package Simulation;

public enum SimulationType {
    GAME_OF_LIFE, SEGREGATION, PREDATOR_PREY, FIRE, PERCOLATION;

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
        }
        return "NULL";
    }
}