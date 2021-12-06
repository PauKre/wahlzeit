package org.wahlzeit.model.coordinate;

import org.wahlzeit.services.DataObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//the superclass is created as a abstract class
public abstract class AbstractCoordinate extends DataObject implements Coordinate{

    @Override
    public double getCentralAngle(Coordinate coordinate) throws ArithmeticException {
        //preconditions
        assertClassInvariants();
        assert coordinate != null;
        coordinate.assertClassInvariants();
        double centralAngle = asSphericCoordinate().getCentralAngle(coordinate);
        //postconditions
        assert 0 <= centralAngle && centralAngle <= (2 * Math.PI);
        assertClassInvariants();
        return centralAngle;
    }

    @Override
    public boolean equals(Object obj) {
        //preconditions
        assertClassInvariants();
        //equals first checks for null and objects of other classes than Coordinate
        if(obj == null || !(obj instanceof Coordinate)){
            return false;
        }
        return isEqual((Coordinate) obj);
    }



    @Override
    public double getDistance(Coordinate coordinate) {
        //preconditions
        assertClassInvariants();
        assert coordinate != null;
        coordinate.assertClassInvariants();
        double cartesianDistance = asCartesianCoordinate().getCartesianDistance(coordinate.asCartesianCoordinate());
        //postconditions
        assert 0 <= cartesianDistance;
        return cartesianDistance;
    }

    //here the actual values are written in the resultSet
    public void writeOn(ResultSet rset) throws SQLException {
        asCartesianCoordinate().writeOn(rset);
    }


    @Override
    public abstract CartesianCoordinate asCartesianCoordinate();

    @Override
    public abstract SphericCoordinate asSphericCoordinate();

    @Override
    public abstract void readFrom(ResultSet rset) throws SQLException;

    @Override
    public abstract boolean isEqual(Coordinate coordinate);

    @Override
    public abstract int hashCode();

    @Override
    public String getIdAsString() {
        return null;
    }

    @Override
    public void writeId(PreparedStatement stmt, int pos) throws SQLException{};

    @Override
    public abstract void assertClassInvariants();


}
