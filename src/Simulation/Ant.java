package Simulation;

import java.util.ArrayList;


public class Ant {


    //heading enum, or some other mapping
    private boolean hasFood;
    private int foodPheromones;
    private int homePheromones;
    private Heading heading;
    private Location location;
    private ArrayList<AntCell> antNeighbors;
    private static final int ANT_MAX_FOOD = 100;
    private static final int ANT_MAX_HOME = 100;
    private static final int ANT_DIFFUSE_HOME = 10;
    private static final int ANT_DIFFUSE_FOOD = 10;

    public Ant(boolean hasFood, Heading heading, Location location) {
        this.hasFood = hasFood;
        this.foodPheromones = 0;
        this.homePheromones = ANT_MAX_HOME;
        this.heading = heading;
        this.location = location;
    }

    public boolean act(AntGrid grid, AntCell antCell){

        updatePheromones((AntState)antCell.getCurrentState());
        antCell.addFood(foodPheromones);
        antCell.addHome(homePheromones);
        //forward neighbors, check if valid
        //if not, check among all neighbors
        antNeighbors = grid.findForwardNeighbors(location, heading);

        if(antNeighbors.isEmpty()){
            antNeighbors = grid.findNeighbors(location);
        }

        if(!antNeighbors.isEmpty()){
            if(hasFood)
                findHome();
            else
                findFood();
            return true;
        }

    return false; //might need var

    }
    private void findHome(){

        //find max home ph neighbor, join them, delete from current

    }

    private void findFood(){
        //find max food ph neighbor, join them, delete from current
    }

    private int dropPheromones(){
        return 1;
        //drop the correct pheromones to parent, decrement the pheromones

    }
    private void updatePheromones(AntState state){
        foodPheromones -= ANT_DIFFUSE_FOOD;
        homePheromones -= ANT_DIFFUSE_HOME;

        if(foodPheromones<0)
            foodPheromones = 0;
        if(homePheromones<0)
            homePheromones = 0;

        switch(state){
            case FOOD:
                foodPheromones = ANT_MAX_FOOD;
                break;
            case HOME:
                homePheromones = ANT_MAX_HOME;
                break;
        }
    }

    public boolean hasFood(){
        return hasFood;
    }


}
