package Simulation;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * State enumeration for Percolation simulation
 */
public enum PercolationState implements State {
    BLOCKED("Blocked", Color.GRAY, 1), OPEN("Open", Color.GHOSTWHITE, 0),
    PERCOLATED("Percolated", Color.CORNFLOWERBLUE, 2);

    private String myLabel;
    private Paint myColor;
    private int myValue;

    PercolationState(String label, Paint color, int value) {
        myLabel = label;
        myColor = color;
        myValue = value;
    }

    /**
     * Returns the color that corresponds to the state
     * @return State color
     */
    @Override
    public Paint getColor(){
        return myColor;
    }

    /**
     * Returns the integer value that corresponds to the state
     * @return State value
     */
    @Override
    public int getValue(){
        return myValue;
    }

    /**
     * Returns String label of state
     * @return State label
     */
    @Override
    public String toString() {
        return myLabel;
    }
}
