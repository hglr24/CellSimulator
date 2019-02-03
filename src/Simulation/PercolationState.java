package Simulation;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public enum PercolationState implements State {
    BLOCKED("Blocked", Color.GRAY), OPEN("Open", Color.GHOSTWHITE), PERCOLATED("Percolated", Color.CORNFLOWERBLUE);

    private String myLabel;
    private Paint myColor;

    PercolationState(String label, Paint color) {
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
