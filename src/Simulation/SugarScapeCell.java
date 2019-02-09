package Simulation;

public class SugarScapeCell extends Cell {
    private int mySugar;
    private int mySugarMax;
    private boolean myAgent;

    public SugarScapeCell(int xPosition, int yPosition, int maxSugar, boolean agent){
        location = new SquareLocation(xPosition, yPosition);
        mySugarMax = maxSugar;
        mySugar = maxSugar;
        myAgent = agent;
    }

}
