package Simulation;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * State enumeration for Segregation simulation
 */
public enum SegregationState implements State {
    EMPTY("Empty", Color.LIGHTGRAY, 0), A("A", Color.INDIANRED, 1),
    B("B", Color.SLATEBLUE, 2);

    private String myLabel;
    private Paint myColor;
    private int myValue;

    SegregationState(String label, Paint color, int value) {
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
