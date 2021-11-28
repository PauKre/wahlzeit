package org.wahlzeit.model.coordinate;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Coordinate {

    public CartesianCoordinate asCartesianCoordinate();

    public double getCartesianDistance(Coordinate coordinate);

    public SphericCoordinate asSphericCoordinate();

    public double getCentralAngle(Coordinate coordinate) throws ArithmeticException;

    public void writeOn(ResultSet rset) throws SQLException;

    public void readFrom(ResultSet rset) throws SQLException;

    public double getDistance(Coordinate coordinate);

    public boolean isEqual(Coordinate coordinate);
}
