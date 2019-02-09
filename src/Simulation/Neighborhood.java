package Simulation;

public enum Neighborhood {
    SQUARE(new int[][]{{0, 0,1, 1,1,-1,-1,-1},
                       {1,-1,1,-1,0,-1, 0, 1}}),

    TRIANGLE(new int[][]{{-1,0,1,-1,-2,1,2,-2,-1,0,-1,2},
                         { 1,1,1, 0, 0,0,0, -1, -1,-1, -1,-1}}),

    HEXAGON(new int[][]{{-1,1,0,1, 0, 1},
                        { 0,0,1,1,-1,-1}}),

    CARDINAL(new int[][]{{ 0,0,-1,1},
                         {-1,1, 0,0}}),

    KNIGHT(new int[][]{{-1, 0,1},
                        {1,-1,0}}),

    I(new int[][]{{-1,0,1,-1, 0, 1},
                  { 1,1,1,-1,-1,-1}}),

    Y(new int[][]{{-1, 0,1},
                   {1,-1,1}});



    private final int[][] deltas;

    Neighborhood(int[][] deltas) {
        this.deltas = deltas;
    }

    public int[][] getDeltas() {
        return deltas;
    }

}
