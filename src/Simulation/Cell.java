package Simulation;


import java.util.List;

public abstract class Cell {

    protected List<Cell> neighbors;
    protected boolean changed;
    protected Location location;
    protected State currentState;
    protected State nextState;

    abstract void findNeighbors();

    protected State determineState(RuleKeeper ruleKeeper){
        return ruleKeeper.determineState(neighbors,location);
    }

    public State getCurrentState(){return currentState;}

    public State getNextState(){return nextState;}

    public boolean hasChanged(){return changed;}
}
