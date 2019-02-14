package Simulation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * Cell class for Ant simulation. Keeps track of its ants, and holds certain parameters
 */
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

    /**
     * Instantiate an AntCell
     * @param xPosition
     * @param yPosition
     * @param initState
     */
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

    private void stateCheck(){
        switch((AntState)currentState){
            case FOOD:
                foodPheromone = MAX_FOOD;
                break;
            case HOME:
                homePheromone = MAX_HOME;
                break;

        }

    }

    /**
     * Make cell and Ant calculations based on grid status
     * @param grid
     */
    public void evaluate(AntGrid grid) {
        stateCheck();

        Iterator<Ant> iter = ants.iterator();

        while (iter.hasNext()) {
            Ant a = iter.next();
            a.act(grid, this);

        }
    }

    /**
     * check if there is room for more ants
     * @return
     */
     boolean available() {
        return ants.size() < MAX_ANTS;
    }

    /**
     * check if there are any ants
     * @return
     */
    boolean hasAnts() {
        return !ants.isEmpty();
    }

    /**
     * check if any of the ants have food
     * @return
     */
     boolean hasFoodAnts() {
        for (Ant a : ants) {
            if (a.hasFood())
                return true;
        }
        return false;
    }

    /**
     * reduce the pheromone levels
     */
     void diffuse() {
        if (foodPheromone > 0)
            foodPheromone -= DIFFUSE_FOOD;
        if (homePheromone > 0)
            homePheromone -= DIFFUSE_HOME;
    }

    /**
     * increment food pheromone
     * @param food
     */
    void addFood(int food) {
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

    /**
     * increment pheromone
     * @param home
     */
     void addHome(int home) {
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

    /**
     * get pheromone
     * @return
     */
     int getFoodPheromone() {
        return foodPheromone;
    }

    /**
     * get pheromone
     * @return
     */
     int getHomePheromone() {
        return homePheromone;
    }

    /**
     * add an ant
     * @param ant
     */
     void addAnt(Ant ant) {
        antsNext.add(ant);
    }

    /**
     * Proceed to state and reset
     */
    @Override
    public void goToNextState(){
        currentState = nextState;
        changed = false;
        ants.clear();
        ants.addAll(antsNext);
        antsNext.clear();
    }

    /**
     * Need to override the method because it should still update even if the state has changed
     * @param grid
     */
    @Override
    public void determineState(Grid grid) {

        nextState = grid.getRuleSet().applyRules(neighbors, this, grid);
        changed = (nextState != currentState);
    }
}


