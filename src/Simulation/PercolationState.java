package Simulation;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public enum PercolationState implements State {
    BLOCKED, OPEN, PERCOLATED;

    @Override
    public Paint getColor() {
        switch(this)
        {
            case BLOCKED:
                return Color.GRAY;
            case OPEN:
                return Color.GHOSTWHITE;
            case PERCOLATED:
                return Color.CORNFLOWERBLUE;
        }
        return null;
    }

    @Override
    public String toString() {
        switch(this)
        {
            case BLOCKED:
                return "Blocked";
            case OPEN:
                return "Open";
            case PERCOLATED:
                return "Percolated";
        }
        return "NULL";
    }
}
