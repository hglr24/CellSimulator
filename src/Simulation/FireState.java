package Simulation;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * State enumeration for Fire simulation
 */
public enum FireState implements State {
    EMPTY("Empty", Color.GRAY, 0), TREE("Tree", Color.PALEGREEN, 1),
    BURNING("Burning", Color.LIGHTCORAL, 2);

    private String myLabel;
    private Paint myColor;
    private int myValue;

    FireState(String label, Paint color, int value) {
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
