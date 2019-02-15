package Simulation;

/**
 * Enumeration that gives SimulationType and maps to the equivalent strings, values, and rulesets
 */
public enum SimulationType {
    GAME_OF_LIFE("Game of Life", GameOfLifeState.values(), new GameOfLifeRuleSet()),
    SEGREGATION("Segregation", SegregationState.values(), new SegregationRuleSet()),
    PREDATOR_PREY("Predator/Prey", PredatorPreyState.values(), new PredatorPreyRuleSet()),
    FIRE("Fire", FireState.values(), new FireRuleSet()),
    PERCOLATION("Percolation", PercolationState.values(), new PercolationRuleSet()),
    RPS("Rock Paper Scissors", RPSState.values(), new RPSRuleSet()),
    ANT("Ant", AntState.values(),new AntRuleSet());

    private String myLabel;
    private State[] myStates;
    private RuleSet myRules;

    SimulationType(String label, State[] states, RuleSet rules) {
        myLabel = label;
        myStates = states;
        myRules = rules;
    }

    /**
     * Creates a ruleset that is used to apply the rules in each simulation
     * @param parameters
     * @return ruleset with the influencing paramters set to the input
     */
    public RuleSet setRules(double[] parameters) {
        myRules.setParameters(parameters);
        return myRules;
    }

    /**
     * Gets a grid of the correct type and casts the ruleset to a ruleset of the correct type
     * @param height is the number of rows of the simulation
     * @param width is the number of columns of the simulation
     * @param initInts is the integer starting configuration
     * @param ruleSet is the governing rules, at this point non-simulation specifice
     * @param neighborhood is the Neighborhood enumeration that determines the neighbors considered in the cell updates
     * @return the Grid containing the cells of the correct SimulationType used in the simulation
     */
    public Grid setGrid(int height, int width, int[][] initInts, RuleSet ruleSet, Neighborhood neighborhood) {
        switch(this) {
            case GAME_OF_LIFE:
                return new GameOfLifeGrid(height, width, initInts, (GameOfLifeRuleSet) ruleSet, neighborhood);
            case SEGREGATION:
                return new SegregationGrid(height, width, initInts, (SegregationRuleSet) ruleSet, neighborhood);
            case PREDATOR_PREY:
                return new PredatorPreyGrid(height, width, initInts, (PredatorPreyRuleSet) ruleSet, neighborhood);
            case FIRE:
                return new FireGrid(height, width, initInts, (FireRuleSet) ruleSet, neighborhood);
            case PERCOLATION:
                return new PercolationGrid(height, width, initInts, (PercolationRuleSet) ruleSet, neighborhood);
            case RPS:
                return new RPSGrid(height, width, initInts, (RPSRuleSet) ruleSet, neighborhood);
            case ANT:
                return new AntGrid(height,width,initInts, (AntRuleSet) ruleSet, neighborhood);
        }
        return new GameOfLifeGrid(height, width, initInts, (GameOfLifeRuleSet) ruleSet, neighborhood);
    }

    /**
     * Return the string corresponding to the type of simulation
     * @return string that maps to the type of simulation
     */
    @Override
    public String toString() {
        return myLabel;
    }

    /**
     * Returns the possible states for the given simulation
     * @return an array of the possible states for the simulation
     */
    public State[] getState() {
        return myStates;
    }

    /**
     * Returns the uncast ruleset for the simulation
     * @return a simulation RuleSet 
     */
    public RuleSet getRules() {
        return myRules;
    }
}