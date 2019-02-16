package UIpackage;

/**
 * Creates a rectangle to be displayed in the simulation grid
 */
class GridRectangle extends GridPolygon {
    private Double[] myCoords;

    GridRectangle(int i, int j, double cellsize) {
        super(i, j, cellsize);
        setCoords();
        this.getPoints().addAll(myCoords);
    }

    @Override
    void setCoords() {
        double size = super.getSize();
        myCoords = new Double[] {
                0.0, 0.0,
                0.0, size,
                size, size,
                size, 0.0
        };
    }
}
