package Simulation;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * State enumeration for SugarScape simulation
 */
public enum SugarScapeState implements State{

    SUGAR2("Sugar2", Color.DARKGREEN, 2),
    SUGAR1("Sugar1", Color.GREEN, 1),
    SUGAR0("Sugar0", Color.WHITE, 0);

    private String myLabel;
    private Paint myColor;
    private int myValue;

    SugarScapeState(String label, Paint color, int value){
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
