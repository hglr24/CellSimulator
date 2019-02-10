package Simulation;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public enum AntState implements State {
    EMPTY("Empty", Color.BROWN), FOOD("Food", Color.PALEGREEN), HOME("Home", Color.LIGHTCORAL), ANT_NO_FOOD("Ant", Color.BLACK), ANT_WITH_FOOD(
            "Ant with Food", Color.ORANGE);

    private String myLabel;
    private Paint myColor;

    AntState(String label, Paint color) {
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
