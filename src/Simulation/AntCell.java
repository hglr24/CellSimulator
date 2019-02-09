package Simulation;

import java.util.ArrayList;
import java.util.Random;

public class AntCell extends Cell {

    private ArrayList<Ant> ants = new ArrayList<>();
    private int foodPheromone = 0;
    private int homePheromone = 0;
    private static final int START_HOME_ANTS = 5;
    private static final int MAX_ANTS = 10;
    private static final int MAX_FOOD = 1000;
    private static final int MAX_HOME = 1000;
    private static final int DIFFUSE_HOME = 10;
    private static final int DIFFUSE_FOOD = 10;

    public AntCell(int xPosition, int yPosition, AntState initState) {
        location = new SquareLocation(xPosition, yPosition);
        currentState = initState;
        nextState = initState;
        if(initState==AntState.HOME){
            Random random = new Random();

            for(int i = 0;i < START_HOME_ANTS; i++)
                ants.add(new Ant(false, 0 , MAX_HOME,Heading.values()[random.nextInt(Heading.values().length)],location));
        }
    }

    public void evaluate( AntGrid grid){
        diffuse();
        for(Ant a :ants){
            a.act(grid);
        }
    }

    private void diffuse(){
        if(foodPheromone>0)
            foodPheromone-=DIFFUSE_FOOD;
        if(homePheromone>0)
            homePheromone-=DIFFUSE_HOME;
    }

    public void addFood(int food){
        foodPheromone += food;
    }

    public void addHome(int home){
        homePheromone += home;
    }

    public boolean available(){
        return ants.size()<MAX_ANTS;
    }


}


