package Simulation;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

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
