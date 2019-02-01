package UIpackage;

import javafx.scene.shape.Polygon;

public class Hexagon extends Polygon {
    private double myRadius;
    private Double[] myCoords;

    public Hexagon(double radius) {
        myRadius = radius;
        setCoords();
        this.getPoints().addAll(myCoords);
    }

    private void setCoords() {
        double lateralLength = Math.sqrt(3) * myRadius;
        myCoords = new Double[] {
                0.0, myRadius / 2,
                0.0, 3 * myRadius / 2,
                lateralLength / 2, 2 * myRadius,
                lateralLength, 3 * myRadius / 2,
                lateralLength, myRadius / 2,
                lateralLength / 2, 0.0,
        };
    }
}
