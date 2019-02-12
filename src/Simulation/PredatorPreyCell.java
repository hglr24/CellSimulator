package Simulation;

public class PredatorPreyCell extends Cell {

    private double sharkEnergyDays;
    private double fishReproduceDays;
    private double sharkReproduceDays;

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

    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }

    public void setNextState(State nextState) {
        this.nextState = nextState;
    }
}

