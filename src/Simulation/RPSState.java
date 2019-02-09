package Simulation;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public enum RPSState implements State {
    ROCK("Rock", Color.RED), PAPER("Paper", Color.BLUE), SCISSORS("Scissors", Color.GREEN);

    private String myLabel;
    private Paint myColor;

    RPSState(String label, Paint color) {
        myLabel = label;
        myColor = color;
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