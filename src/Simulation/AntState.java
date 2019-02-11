package Simulation;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public enum AntState implements State {
    EMPTY("Empty", Color.BROWN,0), FOOD("Food", Color.PALEGREEN,1), HOME("Home", Color.LIGHTCORAL,2), ANT_NO_FOOD("Ant", Color.BLACK,3),
    ANT_WITH_FOOD("Ant with Food", Color.ORANGE,4);


    private String myLabel;
    private Paint myColor;
    private int myValue;

    AntState(String label, Paint color, int value) {
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
