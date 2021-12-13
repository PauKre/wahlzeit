package org.wahlzeit.model.coordinate;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Coordinate {

    public CartesianCoordinate asCartesianCoordinate() throws CoordinateException;

    public SphericCoordinate asSphericCoordinate() throws CoordinateException;

    public double getCentralAngle(Coordinate coordinate) throws IllegalArgumentException, CoordinateException;

    public void writeOn(ResultSet rset) throws SQLException;

    public void readFrom(ResultSet rset) throws SQLException, CoordinateException;

    public double getDistance(Coordinate coordinate) throws IllegalArgumentException, CoordinateException;

    public boolean isEqual(Coordinate coordinate) throws IllegalArgumentException, CoordinateException;

    void assertClassInvariants() throws CoordinateException;
}
