package UIpackage;

import javafx.scene.shape.Polygon;

public class Triangle extends Polygon {
    private double myRadius;
    private Double[] myCoords;

    public Triangle(double radius) {
        myRadius = radius;
        setCoords();
        this.getPoints().addAll(myCoords);
    }

    private void setCoords() {
        double lateralLength = Math.sqrt(3) * myRadius;
        myCoords = new Double[] {
                0.0, 0.0,
                lateralLength / 2, myRadius / 2 + myRadius,
                lateralLength, 0.0
        };
    }
}
