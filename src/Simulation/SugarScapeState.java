package Simulation;

import javafx.scene.paint.Color;

public enum SugarScapeState implements State{

    SUGAR2("Sugar2", Color.DARKGREEN, 2),
    SUGAR1("Sugar1", Color.GREEN, 1),
    SUGAR0("Sugar0", Color.WHITE, 0);

    private String myLabel;
    private Color myColor;
    private int myValue;

    SugarScapeState(String label, Color color, int value){
        myLabel = label;
        myColor = color;
        myValue = value;
    }

    public Color getColor(){
        return myColor;
    }

    public int getValue(){
        return myValue;
    }
}