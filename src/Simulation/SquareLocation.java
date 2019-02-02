package Simulation;

public class SquareLocation implements Location {
    private int x;
    private int y;

    public SquareLocation(int xPos, int yPos){
        x = xPos;
        y = yPos;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }
}
