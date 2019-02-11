package Simulation;

public class SugarScapeAgent {
    private Location myLocation;
    private int mySugar;

    public SugarScapeAgent(Location location, int sugarCount, int metabolism){
        myLocation = location;
        mySugar = sugarCount;
    }

    public void setLocation(Location location){
        myLocation = location;
    }

    public Location getLocation(){
        return myLocation;
    }

    public int getSugarCount(){
        return mySugar;
    }

    public void setSugarCount(int sugarCount){
        mySugar = sugarCount;
    }
}
