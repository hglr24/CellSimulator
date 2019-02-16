package UIpackage;

/**
 * Creates a hexagon to be displayed in the simulation grid
 */
class GridHexagon extends GridPolygon {
    private Double[] myCoords;

    GridHexagon(int i, int j, double radius) {
        super(i, j, radius);
        setCoords();
        this.getPoints().addAll(myCoords);
    }

    @Override
    void setCoords() {
        double size = super.getSize();
        double lateralLength = Math.sqrt(3) * size;
        myCoords = new Double[] {
                0.0, size / 2,
                0.0, 3 * size / 2,
                lateralLength / 2, 2 * size,
                lateralLength, 3 * size / 2,
                lateralLength, size / 2,
                lateralLength / 2, 0.0,
        };
    }
}
