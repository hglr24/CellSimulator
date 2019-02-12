package Simulation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class AntCell extends Cell {

    private ArrayList<Ant> ants = new ArrayList<>();
    private ArrayList<Ant> antsNext = new ArrayList<>();
    private int foodPheromone = 0;
    private int homePheromone = 0;
    private static final int START_HOME_ANTS = 8;
    private static final int MAX_ANTS = 1;
    private static final int DIFFUSE_HOME = 10;
    private static final int DIFFUSE_FOOD = 10;
    private static final int MAX_FOOD = 2000;
    private static final int MAX_HOME = 1000;
    private static final int REDUCTION = 5;


    public AntCell(int xPosition, int yPosition, AntState initState) {
        location = new SquareLocation(xPosition, yPosition);
        currentState = initState;
        nextState = initState;
        if (initState == AntState.HOME) {
            homePheromone = MAX_HOME;
            Random random = new Random();

            for (int i = 0; i < START_HOME_ANTS; i++)
                ants.add(new Ant(false, Heading.values()[random.nextInt(Heading.values().length)], location));

        }

        if (initState == AntState.FOOD) {
            foodPheromone = MAX_FOOD;
        }
    }

    public void stateCheck(){//append for clicking
        switch((AntState)currentState){
            case FOOD:
                foodPheromone = MAX_FOOD;
                break;
            case HOME:
                homePheromone = MAX_HOME;
                break;

        }

    }
    public void evaluate(AntGrid grid) {
        stateCheck();

        Iterator<Ant> iter = ants.iterator();

        while (iter.hasNext()) {
            Ant a = iter.next();
            a.act(grid, this);

        }
    }

    public boolean available() {
        return ants.size() < MAX_ANTS;
    }

    public boolean hasAnts() {
        return !ants.isEmpty();
    }

    public boolean hasFoodAnts() {
        for (Ant a : ants) {
            if (a.hasFood())
                return true;
        }
        return false;
    }

    public void diffuse() {
        if (foodPheromone > 0)
            foodPheromone -= DIFFUSE_FOOD;
        if (homePheromone > 0)
            homePheromone -= DIFFUSE_HOME;
    }

    public void addFood(int food) {
        foodPheromone += food;
        if (foodPheromone > MAX_FOOD)
            foodPheromone = MAX_FOOD;
        int temp = 0;
        for(Cell a: neighbors){
            if(((AntCell) a).getFoodPheromone() > temp)
                temp = ((AntCell) a).getFoodPheromone();
        }
        if(foodPheromone > (temp-REDUCTION))
            foodPheromone = temp-REDUCTION;
    }

    public void addHome(int home) {
        homePheromone += home;
        if (homePheromone > MAX_HOME)
            homePheromone = MAX_HOME;

        int temp = 0;
        for(Cell a: neighbors){
            if(((AntCell) a).getHomePheromone() > temp)
                temp = ((AntCell) a).getHomePheromone();
        }
        if(homePheromone > (temp-REDUCTION))
            homePheromone = temp-REDUCTION;
    }

    public int getFoodPheromone() {
        return foodPheromone;
    }

    public int getHomePheromone() {
        return homePheromone;
    }

    public void addAnt(Ant ant) {
        antsNext.add(ant);
    }

    @Override
    public void goToNextState(){
        currentState = nextState;
        changed = false;
        ants.clear();
        ants.addAll(antsNext);
        antsNext.clear();
    }

    @Override
    public void determineState(Grid grid) {

        nextState = grid.getRuleSet().applyRules(neighbors, this, grid);
        changed = (nextState != currentState);
    }
}


