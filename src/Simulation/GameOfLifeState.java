package Simulation;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public enum GameOfLifeState implements State {
    LIVE("Live", Color.GOLDENROD), DEAD("Dead", Color.GRAY);

    private String myLabel;
    private Paint myColor;

    GameOfLifeState(String label, Paint color) {
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



