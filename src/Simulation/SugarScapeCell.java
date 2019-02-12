package Simulation;

public class SugarScapeCell extends Cell {
    private int mySugar;
    private int mySugarMax;
    private boolean myAgent;
    private int myRegenDays;
    private int myRegenDaysMax;

    public SugarScapeCell(int xPosition, int yPosition, SugarScapeState state, boolean agent, int regenDays){
        location = new SquareLocation(xPosition, yPosition);
        mySugarMax = state.getValue();
        mySugar = state.getValue();
        myAgent = agent;
        myRegenDays = regenDays;
        myRegenDaysMax = regenDays;
    }

    public int getSugar(){
        return mySugar;
    }

    public void setSugar(int sugarCount){
        mySugar = sugarCount;
    }

    public int getMaxSugar(){
        return mySugarMax;
    }

    public boolean getAgent(){
        return myAgent;
    }

    public int getRegenDays(){
        return myRegenDays;
    }

    public void setRegenDays(int regenDays){
        myRegenDays = regenDays;
    }

    public int getMaxRegenDays(){
        return myRegenDaysMax;
    }

    public void setAgent(boolean agent){
        myAgent = agent;
    }
}
