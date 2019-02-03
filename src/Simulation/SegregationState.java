package Simulation;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public enum SegregationState implements State {
    EMPTY("Empty", Color.LIGHTGRAY), A("A", Color.INDIANRED), B("B", Color.SLATEBLUE);

    private String myLabel;
    private Paint myColor;

    SegregationState(String label, Paint color) {
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
