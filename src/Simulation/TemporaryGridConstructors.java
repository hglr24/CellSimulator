package Simulation;

import static Simulation.FireState.EMPTY;
import static Simulation.PercolationState.*;
import static Simulation.PredatorPreyState.FISH;
import static Simulation.PredatorPreyState.SHARK;

public class TemporaryGridConstructors {
   /* public void checkConstructors(){
        PercolationCell[][] initCells = new PercolationCell[height][width];
        for(int k = 0; k < height; k++){
            for(int j = 0; j < width; j++){
                switch(initInts[k][j]){
                    case 0:
                        initCells[k][j] = new PercolationCell(k, j, OPEN);
                    case 1:
                        initCells[k][j] = new PercolationCell(k, j, PERCOLATED);
                    case 2:
                        initCells[k][j] = new PercolationCell(k, j, BLOCKED);
                }
            }
        }

        PredatorPreyCell[][] initCells = new PredatorPreyCell[height][width];
        for(int k = 0; k < height; k++){
            for(int j = 0; j < width; j++){
                switch(initInts[k][j]){
                    case 0:
                        initCells[k][j] = new PercolationCell(k, j, EMPTY);
                    case 1:
                        initCells[k][j] = new PercolationCell(k, j, FISH);
                    case 2:
                        initCells[k][j] = new PercolationCell(k, j, SHARK);
                }
            }
        }
    }*/


}
