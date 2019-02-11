package Simulation;

import java.util.ArrayList;
import java.util.Collections;


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

    public Ant(boolean hasFood, Heading heading, Location location) {
        this.hasFood = hasFood;
        this.foodPheromones = 0;
        this.homePheromones = ANT_MAX_HOME;
        this.heading = heading;
        this.location = (SquareLocation) location;
    }

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

        if(Math.random() <.1){
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
        if(Math.random() <.1){
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

    public boolean hasFood() {
        return hasFood;
    }


}
