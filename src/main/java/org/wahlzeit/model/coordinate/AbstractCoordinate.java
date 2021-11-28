package org.wahlzeit.model.coordinate;

import org.wahlzeit.services.DataObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//the superclass is created as a abstract class
public abstract class AbstractCoordinate extends DataObject implements Coordinate{

    @Override
    public double getCentralAngle(Coordinate coordinate) throws ArithmeticException {
        SphericCoordinate thiz = this.asSphericCoordinate();
        SphericCoordinate other = coordinate.asSphericCoordinate();
        try {
            return(Math.acos(Math.sin(thiz.getTheta())*Math.sin(other.getTheta()) + Math.cos(thiz.getTheta())*Math.cos(other.getTheta())*Math.cos(thiz.getPhi()-other.getPhi())));
        }catch (Exception e){
            throw new ArithmeticException("Error in Calculating the Central Angle!");
        }
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
        CartesianCoordinate thiz = asCartesianCoordinate();
        CartesianCoordinate other = coordinate.asCartesianCoordinate();
        //No nullcheck is provides, as the caller should make sure that the other object is valid
        //the calculation is split into calculating each summand...
        double x_delta_squared = Math.pow(thiz.getX()-other.getX(), 2);
        double y_delta_squared = Math.pow(thiz.getY()-other.getY(), 2);
        double z_delta_squared = Math.pow(thiz.getZ()-other.getZ(), 2);
        //and taking the square root of the sum
        return Math.sqrt(x_delta_squared+y_delta_squared+z_delta_squared);
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
