package org.wahlzeit.model.coordinate;

import org.wahlzeit.services.DataObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//the superclass is created as a abstract class
public abstract class AbstractCoordinate extends DataObject implements Coordinate {

    @Override
    public double getCentralAngle(Coordinate coordinate) throws IllegalArgumentException, CoordinateException {
        //preconditions
        try {
            coordinate.assertClassInvariants();
            assertNotNull(coordinate);
        } catch (Exception ex) {
            throw new IllegalArgumentException("coordinate is not valid");
        }
        try {
            assertClassInvariants();

            double centralAngle = asSphericCoordinate().getCentralAngle(coordinate);
            //postconditions
            assert 0 <= centralAngle && centralAngle <= (2 * Math.PI);
            assertClassInvariants();
            return centralAngle;
        } catch (ArithmeticException arithmeticException) {
            throw new CoordinateException(arithmeticException);
        }
    }

    void assertNotNull(Coordinate coordinate) {
        assert coordinate != null;
    }

    @Override
    public boolean equals(Object obj) {
        //preconditions
        try {
            assertClassInvariants();
            //equals first checks for null and objects of other classes than Coordinate
            if (obj == null || !(obj instanceof Coordinate)) {
                return false;
            }

            return isEqual((Coordinate) obj);
        } catch (CoordinateException coordinateException) {
            coordinateException.printStackTrace();
        }
        return false;
    }


    @Override
    public double getDistance(Coordinate coordinate) throws IllegalArgumentException, CoordinateException {
        //preconditions
        try {
            coordinate.assertClassInvariants();
            assertNotNull(coordinate);
        } catch (Exception ex) {
            throw new IllegalArgumentException("coordinate is not valid");
        }
        assertClassInvariants();
        double cartesianDistance = asCartesianCoordinate().getCartesianDistance(coordinate.asCartesianCoordinate());
        //postconditions
        assert 0 <= cartesianDistance;
        return cartesianDistance;
    }

    //here the actual values are written in the resultSet
    public void writeOn(ResultSet rset) throws SQLException {
        try {
            asCartesianCoordinate().writeOn(rset);
        } catch (CoordinateException coordinateException) {
            coordinateException.printStackTrace();
        }
    }


    @Override
    public abstract CartesianCoordinate asCartesianCoordinate() throws CoordinateException;

    @Override
    public abstract SphericCoordinate asSphericCoordinate() throws CoordinateException;

    @Override
    public abstract void readFrom(ResultSet rset) throws SQLException;

    @Override
    public abstract boolean isEqual(Coordinate coordinate) throws CoordinateException;

    @Override
    public abstract int hashCode();

    @Override
    public String getIdAsString() {
        return null;
    }

    @Override
    public void writeId(PreparedStatement stmt, int pos) throws SQLException {
    }

    ;

    @Override
    public abstract void assertClassInvariants() throws CoordinateException;


}
