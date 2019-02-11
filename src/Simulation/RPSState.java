package Simulation;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public enum RPSState implements State {
    ROCK("Rock", Color.SALMON, 0), PAPER("Paper", Color.CORNFLOWERBLUE, 1),
    SCISSORS("Scissors", Color.PALEGREEN, 2);

    private String myLabel;
    private Paint myColor;
    private int myValue;

    RPSState(String label, Paint color, int value) {
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
