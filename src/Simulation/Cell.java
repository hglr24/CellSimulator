package Simulation;

import java.util.ArrayList;

/**
 * The basic cell. includes many pieces common to all cells in any simulation
 */
public abstract class Cell {

    protected ArrayList<Cell> neighbors;
    protected boolean changed;
    protected Location location;
    protected State currentState;
    protected State nextState;

    /**
     * Determine the next state. Asks the RuleSet.
     * @param grid
     */
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

    /**
     * proceed to next State
     */
    public void goToNextState(){
        currentState = nextState;
        changed = false;
    }

    /**
     * Tell cell to set neighbors based on the grid
     * @param grid
     */
    public void setNeighbors(Grid grid){
        this.neighbors = grid.findNeighbors(getLocation());

    }

    /**
     * Change a specific Cell state from GUI click
     * @param newState
     */
    public void setClickState(State newState) {
        nextState = newState;
        goToNextState();
    }
}
