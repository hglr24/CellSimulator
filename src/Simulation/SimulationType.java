package Simulation;

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

    public RuleSet setRules(double[] parameters) {
        myRules.setParameters(parameters);
        return myRules;
    }

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

    @Override
    public String toString() {
        return myLabel;
    }

    public State[] getState() {
        return myStates;
    }

    public RuleSet getRules() {
        return myRules;
    }
}