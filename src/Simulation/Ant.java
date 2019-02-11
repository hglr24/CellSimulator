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
    private static final int ANT_DIFFUSE_HOME = 10;
    private static final int ANT_DIFFUSE_FOOD = 10;


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
        AntCell optimal = antNeighbors.get(0);
        for (AntCell a : antNeighbors) {
            if (a.getFoodPheromone() > food) {
                food = a.getHomePheromone();
                optimal = a;
            }
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
        move(optimal);
    }

    private void move(AntCell optimal) {
        int diffX = ((SquareLocation) optimal.getLocation()).getX() - location.getX();
        int diffY = ((SquareLocation) optimal.getLocation()).getY() - location.getY();

        for (Heading h : Heading.values()) {
            if (h.relativeY == diffY && h.relativeX == diffX)
                heading = h;
        }
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
                break;
            case HOME:
                homePheromones = ANT_MAX_HOME;
                break;
        }
    }

    public boolean hasFood() {
        return hasFood;
    }


}
