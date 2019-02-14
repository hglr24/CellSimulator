package Simulation;

/**
 * Enumeration to give a direction. Gives information about relative cell calculations
 */
enum Heading{
    N(new int[][]{{-1,0,1},
            { 1,1,1}},0,1),
    NE(new int[][]{{0,1,1},
            { 1,1,0}},1,1),
    E(new int[][]{{1,1,1},
            { 1,0,-1}},1,0),
    SE(new int[][]{{1,1,0},
            { 0,-1,-1}},1,-1),
    S(new int[][]{{-1,0,1},
            { -1,-1,-1}},0,-1),
    SW(new int[][]{{-1,-1,0},
            { 0,-1,-1}},-1,-1),
    W(new int[][]{{-1,-1,-1},
            { 1,0,-1}},-1,0),
    NW(new int[][]{{-1,-1,0},
            { 0,1,1}},-1,1);

    private final int[][] deltas;
    final int relativeX;
    final int relativeY;

    Heading(int[][] deltas, int relativeX, int relativeY) {
        this.deltas = deltas;
        this.relativeX = relativeX;
        this.relativeY = relativeY;
    }

    /**
     * Get relative cell positions for specific headings
     * @return
     */
    public int[][] getDeltas() {
        return deltas;
    }
}