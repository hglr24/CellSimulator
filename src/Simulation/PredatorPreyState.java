package Simulation;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public enum PredatorPreyState implements State {
EMPTY("Empty", Color.LIGHTGRAY), FISH("Fish", Color.SALMON), SHARK("Shark", Color.SLATEBLUE);

    private String myLabel;
    private Paint myColor;

    PredatorPreyState(String label, Paint color) {
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
