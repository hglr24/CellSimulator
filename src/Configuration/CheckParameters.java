package Configuration;

import Simulation.SimulationType;
import java.util.Arrays;

public class CheckParameters {
    double third = 0.33;
    double half = 0.5;
    int defaultChronons = 5;
    int defaultLives = 5;

    public CheckParameters(){
    }

    public double[] checkValidParameters(SimulationType simtype, double[] parameters){
        switch(simtype){
            case FIRE:
                try{
                    if(parameters.length != 1){
                        throw new XMLException("Wrong number of parameters or probabilities. Default enabled.");
                    }
                    checkProbability(parameters);
                }
                catch(XMLException e){
                    double[] defaultParameters = new double[]{half};
                    return defaultParameters;
                }
                return parameters;
            case GAME_OF_LIFE:
            case PERCOLATION:
                try{
                    if(parameters.length != 0){
                        throw new XMLException("Wrong number of parameters. Default enabled.");
                    }
                }
                catch(XMLException e) {
                    double[] defaultParameters = new double[0];
                    return defaultParameters;
                }
                return parameters;
            case PREDATOR_PREY:
                try{
                    if(parameters.length != 3){
                        throw new XMLException("Wrong number of parameters. Default enabled.");
                    }
                    checkInts(parameters);
                }
                catch(XMLException e) {
                    double[] defaultParameters = new double[]{defaultChronons, defaultChronons, defaultLives};
                    return defaultParameters;
                }
                return parameters;
            case SEGREGATION:
                System.out.println("Segregation recognized");
                try{
                    if(parameters.length != 1){
                        throw new XMLException("Wrong number of parameters. Default enabled.");
                    }
                    checkProbability(parameters);
                }
                catch(XMLException e) {
                    double[] defaultParameters = new double[]{0.33};
                    return defaultParameters;
                }
        }
        return parameters;
    }

    public double[] checkValidProbabilities(SimulationType simtype, double[] probabilities){
        switch(simtype){
            case FIRE:
            case SEGREGATION:
            case PREDATOR_PREY:
                try {
                    if (probabilities.length != 3) {
                        System.out.println("Wrong number of probabilites. Default probabilities used");
                        return new double[]{third, third, third};
                    }
                    if (Math.abs(probabilities[0] + probabilities[1] + probabilities[2] - 1) > 0.05) {
                        System.out.println("Probabilities did not add up to one");
                        return new double[]{third, third, third};
                    }
                }
                catch(XMLException e){
                    System.out.println("Numbers given were not probabilities. Default probabilities used.");
                    return new double[]{third, third, third};
                }
                return probabilities;
            case PERCOLATION:
            case GAME_OF_LIFE:
                try {
                    if (probabilities.length != 2) {
                        System.out.println("Wrong number of probabilities. Default probabilities used");
                        return new double[]{half, half};
                    }
                    if (Math.abs(probabilities[0] + probabilities[1] - 1) > 0.05) {
                        System.out.println("Probabilities did not add up to one");
                        return new double[]{half, half};
                    }
                }
                catch(XMLException e){
                    System.out.println("Numbers given were not probabilities. Default probabilities used.");
                    return new double[]{half, half};
                }
                return probabilities;
        }
        return probabilities;
    }

    private void checkInts(double[] parameters){
        for(double d:parameters){
            if(d != Math.round(d) || d < 0){
                throw new XMLException("Integer parameters are not valid. Default values chosen instead.");
            }
        }
    }

    private void checkProbability(double[] parameters){
        for(double d:parameters){
            if(d > 1 || d < 0){
                throw new XMLException("Probability parameters are not valid. Default values chosed instead");
            }
        }
    }
}
