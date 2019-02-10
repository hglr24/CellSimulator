package Simulation;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public enum AntState implements State {
    EMPTY("Empty", Color.GRAY, 0), FOOD("Food", Color.PALEGREEN, 1),
    HOME("Home", Color.LIGHTCORAL, 2);

    private String myLabel;
    private Paint myColor;
    private int myValue;

    AntState(String label, Paint color, int value) {
        myLabel = label;
        myColor = color;
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
