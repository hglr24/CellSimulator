package Simulation;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public enum PredatorPreyState implements State {
EMPTY("Empty", Color.LIGHTGRAY, 0), FISH("Fish", Color.SALMON, 1),
    SHARK("Shark", Color.SLATEBLUE, 2);

    private String myLabel;
    private Paint myColor;
    private int myValue;

    PredatorPreyState(String label, Paint color, int value) {
        myLabel = label;
        myColor = color;
        myValue = value;
    }

    @Override
    public int getValue() {
        return myValue;
    }

    @Override
    public Paint getColor() {
        return myColor;
    }

    @Override
    public String toString() {
        return myLabel;
    }
}
