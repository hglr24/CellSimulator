package Simulation;

import static Simulation.FireState.*;
import static Simulation.GameOfLifeState.DEAD;
import static Simulation.GameOfLifeState.LIVE;
import static Simulation.PercolationState.*;
import static Simulation.PredatorPreyState.FISH;
import static Simulation.PredatorPreyState.SHARK;

public class TemporaryGridConstructors {
    public void checkConstructors(){
        GameOfLifeCell[][] initCells = new GameOfLifeCell[height][width];
        for(int k = 0; k < height; k++){
            for(int j = 0; j < width; j++){
                switch(initInts[k][j]){
                    case 0:
                        initCells[k][j] = new GameOfLifeCell(k, j, DEAD);
                    case 1:
                        initCells[k][j] = new GameOfLifeCell(k, j, LIVE);
                }
            }
        }

        PredatorPreyCell[][] initCells = new PredatorPreyCell[height][width];
        for(int k = 0; k < height; k++){
            for(int j = 0; j < width; j++){
                switch(initInts[k][j]){
                    case 0:
                        initCells[k][j] = new PredatorPreyCell(k, j, EMPTY);
                    case 1:
                        initCells[k][j] = new PredatorPreyCell(k, j, FISH);
                    case 2:
                        initCells[k][j] = new PredatorPreyCell(k, j, SHARK);
                }
            }
        }

        FireCell[][] initCells = new FireCell[height][width];
        for(int k = 0; k < height; k++){
            for(int j = 0; j < width; j++){
                switch(initInts[k][j]){
                    case 0:
                        initCells[k][j] = new FireCell(k, j, EMPTY);
                    case 1:
                        initCells[k][j] = new FireCell(k, j, TREE);
                    case 2:
                        initCells[k][j] = new FireCell(k, j, BURNING);
                }
            }
        }


    }


}
