package Simulation;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

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
