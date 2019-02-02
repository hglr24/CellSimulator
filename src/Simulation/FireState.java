package Simulation;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public enum FireState implements State {
    EMPTY("Empty", Color.GRAY), TREE("Tree", Color.PALEGREEN), BURNING("Burning", Color.LIGHTCORAL);

    private String myLabel;
    private Paint myColor;

    FireState(String label, Paint color) {
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
