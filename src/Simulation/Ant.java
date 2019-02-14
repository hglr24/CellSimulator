package Simulation;

import java.util.ArrayList;
import java.util.Collections;

/**
 * An Ant, used in the Ant simulation to represent individual ants moving about. Ants always belong
 * to an AntCell's ants.
 */
public class Ant {

    private boolean hasFood;
    private int foodPheromones;
    private int homePheromones;
    private Heading heading;
    private SquareLocation location;
    private ArrayList<AntCell> antNeighbors;
    private static final int ANT_MAX_FOOD = 100;
    private static final int ANT_MAX_HOME = 100;
    private static final int ANT_DIFFUSE_HOME = 2;
    private static final int ANT_DIFFUSE_FOOD = 2;
    private static final double RANDOM_FACTOR = .1;

    /**
     * Instantiate a new ant with parameters
     * @param hasFood
     * @param heading
     * @param location
     */
    public Ant(boolean hasFood, Heading heading, Location location) {
        this.hasFood = hasFood;
        this.foodPheromones = 0;
        this.homePheromones = ANT_MAX_HOME;
        this.heading = heading;
        this.location = (SquareLocation) location;
    }

    /**
     * A method for the ant to make the necessary actions based on the environment.
     * Includes a neighbor calculation
     * @param grid
     * @param antCell
     */
    public void act(AntGrid grid, AntCell antCell) {

        updatePheromones((AntState) antCell.getCurrentState());
        antCell.addFood(foodPheromones);
        antCell.addHome(homePheromones);

        antNeighbors = grid.findForwardNeighbors(location, heading);

        if (antNeighbors.isEmpty()) {
            antNeighbors = grid.findNeighbors(location);
        }

        if (!antNeighbors.isEmpty()) {
            if (hasFood)
                findHome();
            else
                findFood();
            return;

        }
        antCell.addAnt(this);


    }

    private void findFood() {
        int food = 0;
        Collections.shuffle(antNeighbors);
        AntCell optimal = antNeighbors.get(0);
        for (AntCell a : antNeighbors) {
            if (a.getFoodPheromone() > food) {
                food = a.getHomePheromone();
                optimal = a;
            }
        }

        if(Math.random() < RANDOM_FACTOR){
            Collections.shuffle(antNeighbors);
            optimal = antNeighbors.get(0);
        }
        move(optimal);
    }

    private void findHome() {
        int home = 0;
        Collections.shuffle(antNeighbors);
        AntCell optimal = antNeighbors.get(0);
        for (AntCell a : antNeighbors) {
            if (a.getHomePheromone() > home) {
                home = a.getHomePheromone();
                optimal = a;
            }
        }
        if(Math.random() < RANDOM_FACTOR){
            Collections.shuffle(antNeighbors);
            optimal = antNeighbors.get(0);
        }

        move(optimal);
    }

    private void move(AntCell optimal) {
        int diffX =  ((SquareLocation) optimal.getLocation()).getX() - location.getX();
        int diffY =  ((SquareLocation) optimal.getLocation()).getY() - location.getX();

        for (Heading h : Heading.values()) {
            if (h.relativeY == diffY && h.relativeX == diffX){
                heading = h;
            }

        }
        location = (SquareLocation) optimal.getLocation();
        optimal.addAnt(this);
    }

    private void updatePheromones(AntState state) {
        foodPheromones -= ANT_DIFFUSE_FOOD;
        homePheromones -= ANT_DIFFUSE_HOME;

        if (foodPheromones < 0)
            foodPheromones = 0;
        if (homePheromones < 0)
            homePheromones = 0;

        switch (state) {
            case FOOD:
                foodPheromones = ANT_MAX_FOOD;
                hasFood = true;
                break;

            case HOME:
                homePheromones = ANT_MAX_HOME;
                hasFood = false;
                break;
        }
    }

    /**
     * Whether or not the ant has food
     * @return
     */
    public boolean hasFood() {
        return hasFood;
    }


}
