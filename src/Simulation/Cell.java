package Simulation;


import java.util.ArrayList;

public abstract class Cell {

    protected ArrayList<Cell> neighbors;
    protected boolean changed;
    protected Location location;
    protected State currentState;
    protected State nextState;

    public void determineState(RuleSet ruleSet) {
        nextState = ruleSet.applyRules(neighbors, this);
        changed = (nextState != currentState);
    }

    public State getCurrentState() {
        return currentState;
    }

    public State getNextState() {
        return nextState;
    }

    public Location getLocation() {
        return location;
    }

    public boolean hasChanged() {
        return changed;
    }

    public void update(){
        currentState = nextState;
    }

    public void setNeighbors(Grid grid){
        this.neighbors = grid.findNeighbors(getLocation());

    }
}
