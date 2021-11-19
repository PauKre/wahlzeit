package org.wahlzeit.model.coordinate;

public interface Coordinate {

    public CartesianCoordinate asCartesianCoordinate();

    public double getCartesianDistance(Coordinate coordinate);

    public SphericCoordinate asSphericCoordinate();

    public double getCentralAngle(Coordinate coordinate);

    public boolean isEqual(Coordinate coordinate);
}
