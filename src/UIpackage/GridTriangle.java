package UIpackage;

class GridTriangle extends GridPolygon {
    private Double[] myCoords;

    GridTriangle(int i, int j, double radius) {
        super(i, j, radius);
        setCoords();
        this.getPoints().addAll(myCoords);
    }

    @Override
    void setCoords() {
        double size = super.getSize();
        double lateralLength = Math.sqrt(3) * size;
        myCoords = new Double[] {
                0.0, 0.0,
                lateralLength / 2, size / 2 + size,
                lateralLength, 0.0
        };
    }
}
