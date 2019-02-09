package Simulation;

public enum Neighborhood {
    SQUARE(new int[][]{{0, 0,1, 1,1,-1,-1,-1},
                       {1,-1,1,-1,0,-1, 0, 1}}),
    TRIANGLE(new int[][]{{-1,0,1,-1,-2,1,2,-2,-1,0,-1,2},
                         { 1,1,1, 0, 0,0,0, 0, 0,0, 0,0}}),

    HEXAGON(new int[][]{{-1,1,0,1, 0, 1},
                        { 0,0,1,1,-1,-1}}),

    CARDINAL(new int[][]{{0 ,0,-1,1},
                         {-1,1, 0,0}});

    private final int[][] deltas;

    Neighborhood(int[][] deltas) {
        this.deltas = deltas;
    }

    public int[][] getDeltas() {
        return deltas;
    }

}
