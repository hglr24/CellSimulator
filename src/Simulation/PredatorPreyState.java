package Simulation;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public enum PredatorPreyState implements State {
    EMPTY, FISH, SHARK;

    @Override
    public Paint getColor() {
        switch(this)
        {
            case EMPTY:
                return Color.GRAY;
            case FISH:
                return Color.SALMON;
            case SHARK:
                return Color.SLATEBLUE;
        }
        return null;
    }

    @Override
    public String toString() {
        switch(this)
        {
            case EMPTY:
                return "Empty";
            case FISH:
                return "Fish";
            case SHARK:
                return "Shark";
        }
        return "NULL";
    }
}
