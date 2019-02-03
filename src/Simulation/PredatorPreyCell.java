package Simulation;

public class PredatorPreyCell extends Cell {

    private int sharkEnergyDays;
    private int fishReproduceDays;
    private int sharkReproduceDays;

    public PredatorPreyCell(int xPosition, int yPosition, PredatorPreyState initState, int sharkEnergyDays, int fishReproduceDays,
                            int sharkReproduceDays) {
        location = new SquareLocation(xPosition, yPosition);
        currentState = initState;
        nextState = initState;
        this.sharkEnergyDays = sharkEnergyDays;
        this.fishReproduceDays = fishReproduceDays;
        this.sharkReproduceDays = sharkReproduceDays;
    }

    public PredatorPreyCell() {
    }

    public int getSharkEnergyDays() {
        return sharkEnergyDays;
    }

    public void setSharkEnergyDays(int sharkEnergyDays) {
        this.sharkEnergyDays = sharkEnergyDays;
    }

    public int getFishReproduceDays() {
        return fishReproduceDays;
    }

    public void setFishReproduceDays(int fishReproduceDays) {
        this.fishReproduceDays = fishReproduceDays;
    }

    public int getSharkReproduceDays() {
        return sharkReproduceDays;
    }

    public void setSharkReproduceDays(int sharkReproduceDays) {
        this.sharkReproduceDays = sharkReproduceDays;
    }
}

