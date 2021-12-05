package org.wahlzeit.model.coordinate;

import org.wahlzeit.services.DataObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//the superclass is created as a abstract class
public abstract class AbstractCoordinate extends DataObject implements Coordinate{

    @Override
    public double getCentralAngle(Coordinate coordinate) throws ArithmeticException {
        return asSphericCoordinate().getCentralAngle(coordinate);
    }

    @Override
    public boolean equals(Object obj) {
        //equals first checks for null and objects of other classes than Coordinate
        if(obj == null || !(obj instanceof Coordinate)){
            return false;
        }
        return isEqual((Coordinate) obj);
    }



    @Override
    public double getCartesianDistance(Coordinate coordinate) {
        return asCartesianCoordinate().getDistance(coordinate.asCartesianCoordinate());
    }

    //here the actual values are written in the resultSet
    public void writeOn(ResultSet rset) throws SQLException {
        CartesianCoordinate thiz = this.asCartesianCoordinate();
        rset.updateDouble("x_coordinate", thiz.getX());
        rset.updateDouble("y_coordinate", thiz.getY());
        rset.updateDouble("z_coordinate", thiz.getZ());
    }

    //calculates cartesian distance
    @Override
    public double getDistance(Coordinate coordinate){
        return asCartesianCoordinate().getDistance(coordinate);
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
    public void writeId(PreparedStatement stmt, int pos) throws SQLException {}

}
