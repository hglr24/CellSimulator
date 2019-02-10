package Simulation;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public enum GameOfLifeState implements State {
    LIVE("Live", Color.GOLDENROD, 1), DEAD("Dead", Color.GRAY, 0);

    private String myLabel;
    private Paint myColor;
    private int myValue;

    GameOfLifeState(String label, Paint color, int value) {
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



