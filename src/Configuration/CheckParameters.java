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

    public double[] checkValid(SimulationType simtype, double[] parameters){
        switch(simtype){
            case FIRE:
                try{
                    if(parameters.length != 4){
                        throw new XMLException("Wrong number of parameters. Default enabled.");
                    }
                    checkProbability(parameters);
                }
                catch(XMLException e){
                    double[] defaultParameters = new double[]{half, third, third, third};
                    return defaultParameters;
                }
                return parameters;
            case GAME_OF_LIFE:
                try{
                    if(parameters.length != 2){
                        throw new XMLException("Wrong number of parameters. Default enabled.");
                    }
                    checkProbability(parameters);
                }
                catch(XMLException e) {
                    double[] defaultParameters = new double[]{half, half};
                    return defaultParameters;
                }
                return parameters;
            case PERCOLATION:
                try{
                    checkProbability(parameters);
                    if(parameters.length != 2){
                        throw new XMLException("Wrong number of parameters. Default enabled.");
                    }
                }
                catch(XMLException e) {
                    double[] defaultParameters = new double[]{half, half};
                    return defaultParameters;
                }
                return parameters;
            case PREDATOR_PREY:
                try{
                    if(parameters.length != 6){
                        throw new XMLException("Wrong number of parameters. Default enabled.");
                    }
                    checkPredatorParams(parameters);
                }
                catch(XMLException e) {
                    double[] defaultParameters = new double[]{defaultChronons, defaultChronons, defaultLives, third, third, third};
                    return defaultParameters;
                }
                return parameters;
            case SEGREGATION:
                System.out.println("Segregation recognized");
                try{
                    if(parameters.length != 4){
                        throw new XMLException("Wrong number of parameters. Default enabled.");
                    }
                    checkProbability(parameters);
                }
                catch(XMLException e) {
                    double[] defaultParameters = new double[]{third, third, third};
                    return defaultParameters;
                }
        }
        return parameters;
    }


    private void checkPredatorParams(double[] parameters){
        double[] probabilityParams = Arrays.copyOfRange(parameters, 3, 5);
        double[] intParams = Arrays.copyOfRange(parameters, 0, 2);
        checkProbability(probabilityParams);
        checkInts(intParams);
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
