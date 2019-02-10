package Simulation;

import java.util.ArrayList;

public abstract class Cell {

    protected ArrayList<Cell> neighbors;
    protected boolean changed;
    protected Location location;
    protected State currentState;
    protected State nextState;

    public void determineState(Grid grid) {
        changed = (nextState != currentState);
        if(hasChanged())
            return;

        nextState = grid.getRuleSet().applyRules(neighbors, this, grid);
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

    public void goToNextState(){
        currentState = nextState;
        changed = false;
    }

    public void setNeighbors(Grid grid){
        this.neighbors = grid.findNeighbors(getLocation());

    }

    public void setClickState(State newState) {
        nextState = newState;
        goToNextState();
    }
}
