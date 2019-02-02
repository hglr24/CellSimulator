package Simulation;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public enum FireState implements State {
    EMPTY, TREE, BURNING;

    @Override
    public Paint getColor() {
        switch(this)
        {
            case EMPTY:
                return Color.GRAY;
            case TREE:
                return Color.PALEGREEN;
            case BURNING:
                return Color.LIGHTCORAL;
        }
        return null;
    }

    @Override
    public String toString() {
        switch(this)
        {
            case EMPTY:
                return "Empty";
            case TREE:
                return "Tree";
            case BURNING:
                return "Burning";
        }
        return "NULL";
    }
}
