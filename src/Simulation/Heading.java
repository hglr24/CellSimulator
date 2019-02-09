package Simulation;

enum Heading{
    N(new int[][]{{-1,0,1},
            { 1,1,1}}),
    NE(new int[][]{{0,1,1},
            { 1,1,0}}),
    E(new int[][]{{1,1,1},
            { 1,0,-1}}),
    SE(new int[][]{{1,1,0},
            { 0,-1,-1}}),
    S(new int[][]{{-1,0,1},
            { -1,-1,-1}}),
    SW(new int[][]{{-1,-1,0},
            { 0,-1,-1}}),
    W(new int[][]{{-1,-1,-1},
            { 1,0,-1}}),
    NW(new int[][]{{-1,-1,0},
            { 0,1,1}});

    private final int[][] deltas;

    Heading(int[][] deltas) {
        this.deltas = deltas;
    }

    public int[][] getDeltas() {
        return deltas;
    }
}