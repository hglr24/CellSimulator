package Simulation;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public enum GameOfLifeState implements State {
    LIVE, DEAD;

    @Override
    public Paint getColor() {
        switch(this)
        {
            case LIVE:
                return Color.PALEGOLDENROD;
            case DEAD:
                return Color.GRAY;
        }
        return null;
    }

    @Override
    public String toString() {
        switch(this)
        {
            case LIVE:
                return "Live";
            case DEAD:
                return "Dead";
        }
        return "NULL";
    }
}



