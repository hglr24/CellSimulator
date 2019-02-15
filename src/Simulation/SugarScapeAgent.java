package Simulation;

/**
 * Unfinished SugarScapeAgent class. Didn't finish the class and some progress had to be deleted
 */
public class SugarScapeAgent {
    private Location myLocation;
    private int mySugar;

    /**
     * Createas a new SugarScapeAgent at the given location with the current sugarCount and location
     * @param location
     * @param sugarCount
     * @param metabolism
     */
    public SugarScapeAgent(Location location, int sugarCount, int metabolism){
        myLocation = location;
        mySugar = sugarCount;
    }

    /**
     * Move the sugarscape agent to a given location
     * @param location is the desired location that the agent is to be moved to
     */
    public void setLocation(Location location){
        myLocation = location;
    }

    /**
     * Returns the current location of the agent
     * @return the current location of the agent
     */
    public Location getLocation(){
        return myLocation;
    }

    /**
     * Returns the current sugar count of the agent
     * @return agent's sugar count
     */
    public int getSugarCount(){
        return mySugar;
    }

    /**
     * Set the agent's sugar count
     * @param sugarCount is the current sugar count of the agent
     */
    public void setSugarCount(int sugarCount){
        mySugar = sugarCount;
    }
}
