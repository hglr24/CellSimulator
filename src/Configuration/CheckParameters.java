package Configuration;

import Simulation.SimulationType;
import UIpackage.ErrorBox;

public class CheckParameters {
    double third = 0.33;
    double half = 0.5;
    int defaultTime = 5;
    int defaultLives = 5;

    /**
     * Create a new checkParameters method that will check if entered parameters and probabilities are valid for
     * a given simulation when the checkValidParameters method is called
     */
    public CheckParameters(){
    }

    /**
     * Check if parameters entered by user or XML are valid and if not, return either default parameters if no
     * parameters had been entered prior or the last valid parameters if there had been valid parameters entered before
     * @param simtype is the SimulationType enumeration of the current simulation
     * @param parameters is the double array of the parameters that you want to check
     * @param newParameters is the set of parameters that the method will turn to if default values are not desired
     * @return the double array of new parameters that the simulation will use
     */
    public double[] checkValidParameters(SimulationType simtype, double[] parameters, double[] newParameters){
        switch(simtype){
            case FIRE:
                try{
                    if(parameters.length != 1){
                        throw new XMLException("Wrong number of parameters or probabilities. Default enabled.");
                    }
                    checkProbability(parameters);
                }
                catch(XMLException e){ ;
                    if(newParameters.length == 1){
                        paramError();
                        return newParameters;
                    }
                    double[] defaultParameters = new double[]{half};
                    System.out.println("Invalid parameters entered. Defaults enabled.");
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
                    System.out.println("Invalid parameters entered. Defaults enabled.");
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
                    System.out.println("Invalid parameters entered. Defaults enabled.");
                    if(newParameters.length == 3){
                        paramError();
                        return newParameters;
                    }
                    double[] defaultParameters = new double[]{defaultTime, defaultTime, defaultLives};
                    return defaultParameters;
                }
                return parameters;
            case SEGREGATION:
                try{
                    if(parameters.length != 1){
                        throw new XMLException("Wrong number of parameters. Default enabled.");
                    }
                    checkProbability(parameters);
                }
                catch(XMLException e) {
                    double[] defaultParameters = new double[]{0.33};
                    if(parameters.length == 1){
                        paramError();
                        return newParameters;
                    }
                    System.out.println("Invalid parameters entered. Defaults enabled.");
                    return defaultParameters;
                }
                return parameters;
            case RPS:
                try{
                    if(parameters.length != 1){
                        throw new XMLException("Wrong number of parameters. Default enabled");
                    }
                    if(parameters[0] < 0 || parameters[0] > 6){
                        throw new XMLException("Parameter must be between 0 and 6");
                    }
                    checkInts(parameters);
                }
                catch(XMLException e){
                    if(newParameters.length != 1){
                        paramError();
                        return newParameters;
                    }
                    System.out.println("Invalid parameters entered. Defaults enabled.");
                    double[] defaultParameters = new double[]{2};
                    return defaultParameters;
                }
        }
        return parameters;
    }

    /**
     * Checks if there are the right number of probabilities for the simulation and makes sure that the probabilities
     * add up to approximately 1
     * @param simtype
     * @param probabilities
     * @return
     */
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

    private void paramError() {
        ErrorBox error = new ErrorBox("Parameter Error",
                "Invalid parameters, reverted to previous value.");
        error.display();
    }
}
