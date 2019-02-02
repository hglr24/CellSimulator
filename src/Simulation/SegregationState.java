package Simulation;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public enum SegregationState implements State {
    EMPTY, A, B;

    @Override
    public Paint getColor() {
        switch(this)
        {
            case EMPTY:
                return Color.GRAY;
            case A:
                return Color.SALMON;
            case B:
                return Color.SKYBLUE;
        }
        return null;
    }

    @Override
    public String toString() {
        switch(this)
        {
            case EMPTY:
                return "Empty";
            case A:
                return "A";
            case B:
                return "B";
        }
        return "NULL";
    }
}
