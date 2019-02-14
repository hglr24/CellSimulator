package Simulation;

/**
 * Predator Prey Cell
 */
public class PredatorPreyCell extends Cell {

    private double sharkEnergyDays;
    private double fishReproduceDays;
    private double sharkReproduceDays;

    /**
     * Instantiate.
     * @param xPosition
     * @param yPosition
     * @param initState
     * @param sharkEnergyDays cycles after which a shark will die if not fed
     * @param fishReproduceDays cycles after which a surviving fish will reproduce
     * @param sharkReproduceDays cycles after which a surviving shark will reproduce
     */
    public PredatorPreyCell(int xPosition, int yPosition, PredatorPreyState initState, double sharkEnergyDays, double fishReproduceDays,
                            double sharkReproduceDays) {
        location = new SquareLocation(xPosition, yPosition);
        currentState = initState;
        nextState = initState;
        this.sharkEnergyDays = sharkEnergyDays;
        this.fishReproduceDays = fishReproduceDays;
        this.sharkReproduceDays = sharkReproduceDays;
    }

    public PredatorPreyCell() {
    }

    public double getSharkEnergyDays() {
        return sharkEnergyDays;
    }

    public void setSharkEnergyDays(Double sharkEnergyDays) {
        this.sharkEnergyDays = sharkEnergyDays;
    }

    public double getFishReproduceDays() {
        return fishReproduceDays;
    }

    public void setFishReproduceDays(Double fishReproduceDays) {
        this.fishReproduceDays = fishReproduceDays;
    }

    public double getSharkReproduceDays() {
        return sharkReproduceDays;
    }

    public void setSharkReproduceDays(Double sharkReproduceDays) {
        this.sharkReproduceDays = sharkReproduceDays;
    }

    public void setNextState(State nextState) {
        this.nextState = nextState;
    }
}

