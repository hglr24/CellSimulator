package Simulation;

import java.util.ArrayList;

public class Ant {


    //heading enum, or some other mapping
    private boolean hasFood;
    private int foodPheremones;
    private int homePheremones;
    private Heading heading;
    private Location location;

    public Ant(boolean hasFood, int foodPheromones, int homePheromones, Heading heading, Location location) {
        this.hasFood = hasFood;
        this.foodPheremones = foodPheromones;
        this.homePheremones = homePheromones;
        this.heading = heading;
        this.location = location;
    }

    public void act(AntGrid grid){
        ArrayList<AntCell> forwardNeighbors = grid.findForwardNeighbors(location, heading);

        if(hasFood)
            findHome();
        else
            findFood();

    }
    private void findHome(){
        //follow go home rules, tell the grid/cells this Ant has moved

    }

    private void findFood(){
        //follow find food rules, tell the grid/cells this Ant has moved
    }

    private int dropPheromones(){
        return 1;
        //drop the correct pheromones to parent, decrement the pheromones

    }

}
