package Simulation;

/**
 * SugarScape cell that tells what the sugar is, the max sugar, whether it shares a location with an agent, the number of
 * chronons until the sugar increases, and how much time in between each regeneration of sugar
 */
public class SugarScapeCell extends Cell {
    private int mySugar;
    private int mySugarMax;
    private boolean myAgent;
    private int myRegenDays;
    private int myRegenDaysMax;

    /**
     * Create a new SugarScapeCell object
     * @param xPosition is the desired horizontal position of the cell
     * @param yPosition is the desired vertical position of the cell
     * @param state is the desired state of the cell
     * @param agent is a boolean signifying if there is an agent sharing the location
     * @param regenDays the number of days it takes to regenerate a sugar
     */
    public SugarScapeCell(int xPosition, int yPosition, SugarScapeState state, boolean agent, int regenDays){
        location = new SquareLocation(xPosition, yPosition);
        mySugarMax = state.getValue();
        mySugar = state.getValue();
        myAgent = agent;
        myRegenDays = regenDays;
        myRegenDaysMax = regenDays;
    }

    /**
     *  Returns the current amount of sugar in the cell
     * @return int representing the amount of sugar in the cell
     */
    public int getSugar(){
        return mySugar;
    }

    /**
     * Sets the current sugar in the cell
     * @param sugarCount is the int representing the amount of sugar in the cell
     */
    public void setSugar(int sugarCount){
        mySugar = sugarCount;
    }

    /**
     * Returns the max sugar that the cell can reach to check if a cell can gain another sugar
     * @return the integer representation of the maximum sugar allowed in the cell
     */
    public int getMaxSugar(){
        return mySugarMax;
    }

    /**
     * Tells whether this cell shares a location with an agent
     * @return boolean telling whether an agent is at this cell
     */
    public boolean getAgent(){
        return myAgent;
    }

    /**
     * Gets the number of days left until the cell can regenerate another sugar
     * @return integer representing the number of days until this cell can regenerate a sugar
     */
    public int getRegenDays(){
        return myRegenDays;
    }

    /**
     * Set the number of days until this cell can regenerate another sugar
     * @param regenDays is the number of days until the cell can regenerate another sugar
     */
    public void setRegenDays(int regenDays){
        myRegenDays = regenDays;
    }

    /**
     * Gets the overall number of days that it takes for the cell to regenerate a sugar
     * @return the integer representing the maximum number of days it takes to regenerate a sugar
     */
    public int getMaxRegenDays(){
        return myRegenDaysMax;
    }

    /**
     * Sets whether an agent shares this location with the cell
     * @param agent is true if the agent is sharing the cell or false if it is not
     */
    public void setAgent(boolean agent){
        myAgent = agent;
    }
}
